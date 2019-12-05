package com.github.twilliams1504.interviewtask;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final AttendeeRepository attendeeRepository;
    private static final VoteRepository voteRepository;
    private static final AppointmentRepository appointmentRepository;

    static {
        //Change to `new MongoClient("<host>:<port>")`
        //if you don't have Mongo running locally on port 27017
        MongoDatabase mongo = new MongoClient().getDatabase("newDB");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        voteRepository = new VoteRepository(mongo.getCollection("votes"));
        
        attendeeRepository = new AttendeeRepository(
        		new JsonFileListOfAttendee("data/attendeeDB.json"));
        
        appointmentRepository = new AppointmentRepository(
        		new JsonFileListOfAppointment("data/appointmentDB.json"));
    }
    
    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository, attendeeRepository, voteRepository, appointmentRepository), 
                		new Mutation(linkRepository, attendeeRepository, voteRepository, appointmentRepository),
                		new VoteResolver(linkRepository, attendeeRepository),
                		new AppointmentResolver(attendeeRepository))
                .scalars(Scalars.dateTime)
                .build()
                .makeExecutableSchema();
    }
    
    @Override
    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        return errors.stream()
                .filter(e -> e instanceof ExceptionWhileDataFetching || super.isClientError(e))
                .map(e -> e instanceof ExceptionWhileDataFetching ? new SanitizedError((ExceptionWhileDataFetching) e) : e)
                .collect(Collectors.toList());
    }
}
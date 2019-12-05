package com.github.twilliams1504.interviewtask;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Mutation implements GraphQLRootResolver {
    
    private final LinkRepository linkRepository;
    private final AttendeeRepository attendeeRepository;
    private final VoteRepository voteRepository;
    private final AppointmentRepository appointmentRepository;

    public Mutation(LinkRepository linkRepository, 
    		AttendeeRepository attendeeRepository,
    		VoteRepository voteRepository,
    		AppointmentRepository appointmentRepository) {
        this.linkRepository = linkRepository;
        this.attendeeRepository = attendeeRepository;
        this.voteRepository = voteRepository;
        this.appointmentRepository = appointmentRepository;
    }
    
    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
    
    public Attendee createAttendee(String name) {
        Attendee newAttendee = new Attendee(name);
        attendeeRepository.saveAttendee(newAttendee);
        return newAttendee;
    }
    
    public Vote createVote(String attendeeId, String linkId) {
        ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
    	Vote newVote= new Vote(now, attendeeId, linkId);
        voteRepository.saveVote(newVote);
        return newVote;
    }
    
    //implements mutation { createAppointment(location:String!, attendeeIds: [ID]!): Appointment }
    public Appointment createAppointment(String location, List<String> attendeeIds)
    {
    	Appointment newAppointment = new Appointment(location, attendeeIds);
    	appointmentRepository.saveAppointment(newAppointment);
    	return newAppointment;
    }
}

package com.github.twilliams1504.interviewtask;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.*;

public class Query implements GraphQLRootResolver {
    
    private final LinkRepository linkRepository;
    private final AttendeeRepository attendeeRepository;
    private final VoteRepository voteRepository;
    private final AppointmentRepository appointmentRepository;

    public Query(LinkRepository linkRepository, 
    		AttendeeRepository attendeeRepository,
    		VoteRepository voteRepository,
    		AppointmentRepository appointmentRepository) {
        this.linkRepository = linkRepository;
        this.attendeeRepository = attendeeRepository;
        this.voteRepository = voteRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
    
    public List<Attendee> allAttendees() {
    	return attendeeRepository.getAllAttendees();
    }
    
    //implements query{ allVotes: [Votes] }
    public List<Vote> allVotes() {
    	return voteRepository.getAllVotes();
    }
    
    //implements query { allAppointments: [Appointment] }
    public List<Appointment> allAppointments() {
    	return appointmentRepository.getAllAppointments();
    }
}

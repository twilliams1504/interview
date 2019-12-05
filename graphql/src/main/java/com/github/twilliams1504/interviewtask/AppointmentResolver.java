package com.github.twilliams1504.interviewtask;

import com.coxautodev.graphql.tools.GraphQLResolver;
import java.util.*;

public class AppointmentResolver implements GraphQLResolver<Appointment> {
    
    private final AttendeeRepository attendeeRepository;

    AppointmentResolver(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> attendees(Appointment appointment) {
    	return attendeeRepository.findByIds(appointment.getAttendeeIds());
    }
    
    
}

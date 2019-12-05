package com.github.twilliams1504.interviewtask;

import java.util.List;

public class AppointmentRepository {
	
	private final JsonFileListOfAppointment jsonDB;
    private List<Appointment> cache;

    public AppointmentRepository(JsonFileListOfAppointment jsonDB) {
        this.jsonDB = jsonDB;
        this.cache = jsonDB.getList();
    }

    //Must be implemented for 1:1 object relations for sub-queries
    public Appointment findById(String id) {
    	//First, check cache
    	Appointment record = cache.stream()
    			  .filter(appointment -> id.equals(appointment.getId()))
    			  .findAny()
    			  .orElse(null);
    	
    	//Next, check disk, and add to cache
    	// (Not implemented since cache is always entire list)
    	return record;
    }
    
   
    
    
    public List<Appointment> getAllAppointments() {
        return cache;
    }
    
    public Appointment saveAppointment(Appointment appointment) {
    	//In this simple case, add to the cache, which mirrors the database
    	// Assign sequential ID (assumes that no Appointments have been removed from list)
    	Appointment newAppointment = new Appointment(Integer.toString(jsonDB.getSize()),
    			appointment.getLocation(),
    			appointment.getAttendeeIds());
    	cache.add(newAppointment);
    	
    	//Push update to database
    	jsonDB.putList(cache);	
    	
    	return newAppointment;
    }

}

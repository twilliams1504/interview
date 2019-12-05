package com.github.twilliams1504.interviewtask;
import java.util.*;

public class Appointment {
	
	/*implements from Schema
	   type Appointment {
		  id: ID!
		  location: String!
		  attendees: [Attendee!]!
		}
	 */
	
	
	private final String id;
	private final String location;
	private final List<String> attendeeIds;

	public Appointment(String location, List<String> attendeeIds) {
		this.id = null;
		this.location = location;
		this.attendeeIds = attendeeIds;
	}
	
	public Appointment(String id, String location, List<String> attendeeIds) {
		this.id = id;
		this.location = location;
		this.attendeeIds = attendeeIds;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public List<String> getAttendeeIds() {
		return attendeeIds;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", location=" + location + ", attendeeIds=" + attendeeIds + "]";
	}
	
}

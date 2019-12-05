package com.github.twilliams1504.interviewtask;

public class Test {
	public static void main(String[] args)
	{
		AttendeeRepository attendeeRepository = new AttendeeRepository(
        		new JsonFileListOfAttendee("data/attendeeDB.json"));
		
		System.out.println(attendeeRepository.getAllAttendees());
		
		Attendee a1 = attendeeRepository.findById("0");
		System.out.println(a1);
	}

}

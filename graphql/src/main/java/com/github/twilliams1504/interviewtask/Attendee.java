package com.github.twilliams1504.interviewtask;

public class Attendee{
	
	private final String id;
	private final String name;
	
	//Copy constructor for assigning Id through DB
	public Attendee(String id, Attendee copy)
	{
		this.id = id;
		this.name = copy.getName();
	}
	
	public Attendee(String name) {
		this(null, name);
	}


	public Attendee(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public String getId()
	{
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Attendee [id=" + id + ", name=" + name + "]";
	}

}

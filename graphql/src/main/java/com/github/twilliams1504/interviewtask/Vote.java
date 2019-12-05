package com.github.twilliams1504.interviewtask;
import java.time.ZonedDateTime;

public class Vote {

	private final String id;
	private final ZonedDateTime createdAt;
	private final String attendeeId; //requires resolver<Vote>
	private final String linkId; // requires resolver<Vote>
	
	
	
	public Vote(ZonedDateTime createdAt, String attendeeId, String linkId) {
		this.id = null;
		this.createdAt = createdAt;
		this.attendeeId = attendeeId;
		this.linkId = linkId;
	}


	public Vote(String id, ZonedDateTime createdAt, String attendeeId, String linkId) {
		this.id = id;
		this.createdAt = createdAt;
		this.attendeeId = attendeeId;
		this.linkId = linkId;
	}


	public String getId() {
		return id;
	}


	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}


	public String getAttendeeId() {
		return attendeeId;
	}


	public String getLinkId() {
		return linkId;
	}


	@Override
	public String toString() {
		return "Vote [id=" + id + ", createdAt=" + createdAt + ", attendeeId=" + attendeeId + ", linkId=" + linkId
				+ "]";
	}
	
	
	
	
	

	
	
	
	
	
}

package com.github.twilliams1504.interviewtask;

import static com.mongodb.client.model.Filters.eq;

import java.util.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

public class AttendeeRepository {
    
	/**
	 * MongoDB Implementation
	 *
	private final MongoCollection<Document> attendees;

    public AttendeeRepository(MongoCollection<Document> attendees) {
        this.attendees = attendees;
    }

	//Must be implemented for object relations so that other
    // types can run sub-queries
    public Attendee findById(String id) {
        Document doc = attendees.find(eq("_id", new ObjectId(id))).first();
        return attendee(doc);
    }
    
    
    public List<Attendee> getAllAttendees() {
        List<Attendee> allLinks = new ArrayList<>();
        for (Document doc : attendees.find()) {
            allLinks.add(attendee(doc));
        }
        return allLinks;
    }
    
    public void saveAttendee(Attendee attendee) {
        Document doc = new Document();
        doc.append("name", attendee.getName());
        attendees.insertOne(doc);
    }
    
    private Attendee attendee(Document doc) {
        return new Attendee(
                doc.get("_id").toString(),
                doc.getString("name"));
    }
	*/
	
	
	
	
	/**
	 * JSON Generic Approach (doesn't work when Jetty is restarted
	 *  because type erasure happens at run time).
	 *   There is a fix, but it's a bit of work. See:
	 *    https://stackoverflow.com/questions/14503881/strange-behavior-when-deserializing-nested-generic-classes-with-gson/14506181#14506181
	 *    https://stackoverflow.com/questions/26203634/gson-fromjson-deserialize-generics/26203635#26203635
	 * 
	private final JsonFlatList<Attendee> jsonDB;
    private List<Attendee> cache;

    public AttendeeRepository(JsonFlatList<Attendee> jsonDB) {
        this.jsonDB = jsonDB;
        this.cache = jsonDB.getList();
    }

    public Attendee findById(String id) {
    	//First, check cache
    	Attendee record = cache.stream()
    			  .filter(attendee -> id.equals(attendee.getId()))
    			  .findAny()
    			  .orElse(null);
    	
    	//Next, check disk, and add to cache
    	// (Not implemented since cache is always entire list)
    	return record;
    }
    
    public List<Attendee> getAllAttendees() {
        return cache;
    }
    
    public Attendee saveAttendee(Attendee attendee) {
    	//In this simple case, add to the cache, which mirrors the database
    	// Assign sequential ID (assumes that no Attendees have been removed from list)
    	Attendee newAttendee = new Attendee(Integer.toString(jsonDB.getSize()),attendee);
    	cache.add(newAttendee);

    	//Push update to database
    	jsonDB.putList(cache);	
    	
    	return newAttendee;
    }*/
    
    
	
	
	/**
	 * 
	 * JSON Backend Implementation
	 * 
	 * 
	 */
    private final JsonFileListOfAttendee jsonDB;
    private List<Attendee> cache;

    public AttendeeRepository(JsonFileListOfAttendee jsonDB) {
        this.jsonDB = jsonDB;
        this.cache = jsonDB.getList();
    }

    //Must be implemented for 1:1 dependencies for subquery
    public Attendee findById(String id) {
    	//First, check cache
    	Attendee record = cache.stream()
    			  .filter(attendee -> id.equals(attendee.getId()))
    			  .findAny()
    			  .orElse(null);
    	
    	//Next, check disk, and add to cache
    	// (Not implemented since cache is always entire list)
    	return record;
    }
    
  //Must be implemented for 1:many dependencies for subquery
    public List<Attendee> findByIds(List<String> ids) {
    	//First, check cache
    	List<Attendee> records = new ArrayList<Attendee>();
    	for (Attendee a : cache) 
    		if (ids.contains(a.getId()))
    			records.add(a);
    	
    	//Next, check disk, and add to cache
    	// (Not implemented since cache is always entire list)
    	return records;
    }
    
    public List<Attendee> getAllAttendees() {
        return cache;
    }
    
    public Attendee saveAttendee(Attendee attendee) {
    	//In this simple case, add to the cache, which mirrors the database
    	// Assign sequential ID (assumes that no Attendees have been removed from list)
    	Attendee newAttendee = new Attendee(Integer.toString(jsonDB.getSize()),attendee);
    	cache.add(newAttendee);
    	
    	//Push update to database
    	jsonDB.putList(cache);	
    	
    	return newAttendee;
    }
    
}

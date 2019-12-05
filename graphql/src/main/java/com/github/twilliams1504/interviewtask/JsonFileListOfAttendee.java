package com.github.twilliams1504.interviewtask;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;

//For file reading and writing
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

/* Class encapsulates file access and reading/writing 
 *  to and from JSON for a List<Object> that could
 *  serve as a database for a repository.
 *  
 *  Includes a simple sequential ID generator that 
 *  assumes that no objects are ever removed from 
 *  the database.
 */
public class JsonFileListOfAttendee {

    private final String jsonFilename;
    private Type listOfTType;
    private int size;
    
    private Gson gson;
    
    
    public JsonFileListOfAttendee(String jsonFilename) {
    	//Initialize gson object and get Type from T
    	gson = new Gson();
    	this.listOfTType = new TypeToken<List<Attendee>>() {}.getType();
    	
    	//Creates new DB if file doesn't exist 
    	this.jsonFilename = jsonFilename;
    	File jsonFile = new File(jsonFilename);
    	
    	boolean isNewDB = false;
		try {
			isNewDB = jsonFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		//Set nextId based on length of current list from file
		if (isNewDB) {
    		List<Attendee> emptyList = new ArrayList<Attendee>();
    		jsonToFile(gson.toJson(emptyList), jsonFilename);
    		size = 0;
    	}
		
		else {
			List<Attendee> list = getList();
			size = list.size();
		}
    }
    
    public List<Attendee> getList()
    {
    	String jsonString = jsonFromFile(jsonFilename);
    	return ((List<Attendee>) gson.fromJson(jsonString, listOfTType));

    }
    
    /*Preconditions: id values in list
     * have been sequentially set, i.e. 
     *  The values of T.id for each T 
     *  in list form the set of integers 
     *  in the range [0,L-1] for L: length of list
     */
    public void putList(List<Attendee> list)
    {
    	jsonToFile(gson.toJson(list), jsonFilename);
    	size = list.size();
    }
   
    
    
    public int getSize()
    {
    	return size;
    }
    
    
    //From:
    //https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
    private static String jsonFromFile(String filePath) 
    {	
    	String content = "";
        try {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    
    //From:
    //https://howtodoinjava.com/java8/java-8-write-to-file-example/
    private static boolean jsonToFile (String jsonString, String filePath)
    {
    	try {
			Files.write(Paths.get(filePath), jsonString.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
    }
    

}



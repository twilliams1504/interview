package com.github.twilliams1504.interviewtask;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class VoteRepository {
    
    private final MongoCollection<Document> votes;

    public VoteRepository(MongoCollection<Document> votes) {
        this.votes = votes;
    }

    public List<Vote> findByAttendeeId(String attendeeId) {
        List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("attendeeId", attendeeId))) {
            list.add(vote(doc));
        }
        return list;
    }

    public List<Vote> findByLinkId(String linkId) {
        List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("linkId", linkId))) {
            list.add(vote(doc));
        }
        return list;
    }
    
    public List<Vote> getAllVotes() {
        List<Vote> allVotes = new ArrayList<>();
        for (Document doc : votes.find()) {
            allVotes.add(vote(doc));
        }
        return allVotes;
    }

    public Vote saveVote(Vote vote) {
        Document doc = new Document();
        doc.append("attendeeId", vote.getAttendeeId());
        doc.append("linkId", vote.getLinkId());
        doc.append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()));
        votes.insertOne(doc);
        return new Vote(
                doc.get("_id").toString(),
                vote.getCreatedAt(),
                vote.getAttendeeId(),
                vote.getLinkId());
    }
    
    private Vote vote(Document doc) {
        return new Vote(
                doc.get("_id").toString(),
                ZonedDateTime.parse(doc.getString("createdAt")),
                doc.getString("attendeeId"),
                doc.getString("linkId")
        );
    }
}

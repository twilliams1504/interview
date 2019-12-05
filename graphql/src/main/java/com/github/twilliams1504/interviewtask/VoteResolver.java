package com.github.twilliams1504.interviewtask;

import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Contains vote sub-queries
 */
public class VoteResolver implements GraphQLResolver<Vote> {
    
    private final LinkRepository linkRepository;
    private final AttendeeRepository attendeeRepository;

    VoteResolver(LinkRepository linkRepository, AttendeeRepository attendeeRepository) {
        this.linkRepository = linkRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee attendee(Vote vote) {
        return attendeeRepository.findById(vote.getAttendeeId());
    }
    
    public Link link(Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    }
}

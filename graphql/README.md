## Basic GraphQL Service

The following technical session is open-ended, and designed to simulate collaborating on the technical aspects of a hypothetical sprint task, which we would not expect to be completed within our 90 minute block. 
  
During the first part of the interview, we will discuss the task in general terms. At this point, depending on the experience and knowledge of the candidate, we will target more specific areas for a deeper discussion; for example, we may dive into schema design and discuss specific data scenarios, or maybe container architecture, or else we might skip all of that and work on serializing/deserializing objects to s3. If you show deep knowledge, we might quickly move on to another topic; but at some point, we will write code. For example, we might focus on implementing working code to serialize/deserialize objects into json files, using popular libraries. Or, for more senior candidates, we might stand up a boilerplate web service and quickly copy-and-paste our way to a prototype as an example.

The goal of the interview is to assess our ability to work together on a "representative" task, and to evaluate your particular combination of breadth, depth and capacity against a common framework. 

The hypothetical sprint task is as follows:
  
> As an internal developer, I need a graphql endpoint that supports three **basic** objects: 
>   - `Meeting Room` 
>   - `Appointment`
>   - `Attendee`
>   
> The endpoint should be able to perform basic queries, and can add/remove `Appointments` from `Meeting Rooms`
> 
> Additionally, the endpoint should persist (and rehydrate) all data to s3."

Study Hints:
The following (unordered) list of skills are all valuable (though no *single* skill is required):
- basic understanding of graphql
- ability to quickly find and stand up a "hello world" graphql boilerplate service of some kind
- familiarity with adding basic schema and mutations to the boilerplate
- ability to quickly serialize/deserialize objects into s3 storage (json? avro?)
- caching; assuming you are saving data in s3, when/how would you update disk?
  

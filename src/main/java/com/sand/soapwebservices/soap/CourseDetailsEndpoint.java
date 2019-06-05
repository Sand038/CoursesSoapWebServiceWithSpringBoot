package com.sand.soapwebservices.soap;

import com.sand.courses.CourseDetails;
import com.sand.courses.GetCourseDetailsRequest;
import com.sand.courses.GetCourseDetailsResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint
{
  @PayloadRoot(namespace = "http://com/sand/courses", localPart = "GetCourseDetailsRequest")
  @ResponsePayload
  public GetCourseDetailsResponse processCourseDetailsRequest(
      @RequestPayload
          GetCourseDetailsRequest courseDetailsRequest)
  {
    GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();
    CourseDetails courseDetails = new CourseDetails();
    courseDetails.setId(courseDetailsRequest.getId());
    courseDetails.setName("Microservices Course");
    courseDetails.setDescription("This course is about the basics of microservices");
    courseDetailsResponse.setCourseDetails(courseDetails);
    return courseDetailsResponse;
  }
}

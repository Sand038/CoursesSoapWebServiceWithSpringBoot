package com.sand.soapwebservices.soap;

import java.util.List;

import com.sand.courses.CourseDetails;
import com.sand.courses.DeleteCourseDetailsRequest;
import com.sand.courses.DeleteCourseDetailsResponse;
import com.sand.courses.GetAllCourseDetailsRequest;
import com.sand.courses.GetAllCourseDetailsResponse;
import com.sand.courses.GetCourseDetailsRequest;
import com.sand.courses.GetCourseDetailsResponse;
import com.sand.soapwebservices.soap.bean.Course;
import com.sand.soapwebservices.soap.service.CourseDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint
{
  @Autowired
  private CourseDetailsService courseDetailsService;

  @PayloadRoot(namespace = "http://com/sand/courses", localPart = "GetCourseDetailsRequest")
  @ResponsePayload
  public GetCourseDetailsResponse processCourseDetailsRequest(
      @RequestPayload
          GetCourseDetailsRequest courseDetailsRequest)
  {

    Course course = courseDetailsService.findById(courseDetailsRequest.getId());
    return mapToCourseDetailsResponse(course);
  }

  @PayloadRoot(namespace = "http://com/sand/courses", localPart = "GetAllCourseDetailsRequest")
  @ResponsePayload
  public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
      @RequestPayload
          GetAllCourseDetailsRequest allCourseDetailsRequest)
  {

    List<Course> courses = courseDetailsService.findAll();
    return mapAllToCourseDetailsResponse(courses);
  }

  @PayloadRoot(namespace = "http://com/sand/courses", localPart = "DeleteCourseDetailsRequest")
  @ResponsePayload
  public DeleteCourseDetailsResponse deleteCourseDetailsRequest(
      @RequestPayload
          DeleteCourseDetailsRequest deleteCourseDetailsRequest)
  {
    DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
    deleteCourseDetailsResponse.setStatus(courseDetailsService.deleteById(deleteCourseDetailsRequest.getId()));
    return deleteCourseDetailsResponse;
  }

  private GetCourseDetailsResponse mapToCourseDetailsResponse(Course course)
  {
    GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();
    courseDetailsResponse.setCourseDetails(mapToCourseDetails(course));
    return courseDetailsResponse;
  }

  private GetAllCourseDetailsResponse mapAllToCourseDetailsResponse(List<Course> courses)
  {
    GetAllCourseDetailsResponse allCourseDetailsResponse = new GetAllCourseDetailsResponse();
    for (Course course : courses)
    {
      CourseDetails courseDetails = mapToCourseDetails(course);
      allCourseDetailsResponse.getCourseDetails().add(courseDetails);
    }
    return allCourseDetailsResponse;
  }

  private CourseDetails mapToCourseDetails(Course course)
  {
    CourseDetails courseDetails = new CourseDetails();
    courseDetails.setId(course.getId());
    courseDetails.setName(course.getName());
    courseDetails.setDescription(course.getDescription());
    return courseDetails;
  }
}

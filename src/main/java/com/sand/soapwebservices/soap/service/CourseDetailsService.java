package com.sand.soapwebservices.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sand.soapwebservices.soap.bean.Course;

import org.springframework.stereotype.Component;

@Component
public class CourseDetailsService
{
  private static List<Course> courses = new ArrayList<>();

  public enum Status
  {
    SUCCESS, FAILURE
  }

  static
  {
    Course course1 = new Course(1, "Spring", "Spring description");
    courses.add(course1);

    Course course2 = new Course(2, "Spring Boot", "Spring Boot description");
    courses.add(course2);

    Course course3 = new Course(3, "SOAP", "SOAP description");
    courses.add(course3);

    Course course4 = new Course(4, "REST", "REST description");
    courses.add(course4);
  }

  public Course findById(int id)
  {
    for (Course course : courses)
    {
      if (course.getId() == id)
      {
        return course;
      }
    }
    return null;
  }

  public List<Course> findAll()
  {
    return courses;
  }

  public Status deleteById(int id)
  {
    Iterator<Course> iterator = courses.iterator();
    while (iterator.hasNext())
    {
      Course course = iterator.next();
      if (course.getId() == id)
      {
        iterator.remove();
        return Status.SUCCESS;
      }
    }
    return Status.FAILURE;
  }
}

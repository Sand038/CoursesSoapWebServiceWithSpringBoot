package com.sand.soapwebservices.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException
{
  public CourseNotFoundException(String message)
  {
    super(message);
  }
}

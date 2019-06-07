package com.sand.soapwebservices.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebserviceConfig extends WsConfigurerAdapter
{
  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
  {
    MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
    messageDispatcherServlet.setApplicationContext(applicationContext);
    messageDispatcherServlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
  }

  @Bean(name = "courses")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema)
  {
    DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
    definition.setPortTypeName("CoursePort");
    definition.setTargetNamespace("http://com/sand/courses");
    definition.setLocationUri("/ws");
    definition.setSchema(xsdSchema);

    return definition;
  }

  @Bean
  public XsdSchema coursesSchema()
  {
    return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
  }

  @Bean
  public XwsSecurityInterceptor securityInterceptor()
  {
    XwsSecurityInterceptor xwsSecurityInterceptor = new XwsSecurityInterceptor();
    xwsSecurityInterceptor.setCallbackHandler(callbackHandler());
    xwsSecurityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
    return xwsSecurityInterceptor;
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors)
  {
    interceptors.add(securityInterceptor());
  }

  @Bean
  public SimplePasswordValidationCallbackHandler callbackHandler()
  {
    SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
    handler.setUsersMap(Collections.singletonMap("User", "Password"));
    return handler;
  }
}

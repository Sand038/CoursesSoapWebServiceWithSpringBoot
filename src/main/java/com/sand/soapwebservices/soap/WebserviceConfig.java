package com.sand.soapwebservices.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WebserviceConfig
{
  @Bean
  ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
  {
    MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
    messageDispatcherServlet.setApplicationContext(applicationContext);
    messageDispatcherServlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
  }
}

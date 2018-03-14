package com.globallogic.queuesTest;

import javax.jms.ConnectionFactory;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Application {
	
  public static void main(String[] args) {
	  SpringApplication.run(Application.class, args);
  }
  
  @Bean
  public BrokerService brokerService() throws Exception {
      BrokerService broker = new BrokerService();
      broker.addConnector("tcp://localhost:61616");
      broker.setPersistent(false);
      return broker;
  }
    
	
}

package com.globallogic.queuesTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {

	 @Bean
	  public Receiver receiver() {
	    return new Receiver();
	  }

}

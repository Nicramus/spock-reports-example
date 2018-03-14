package com.globallogic.queuesTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {	 
	@Bean
	public Sender sender() {
		return new Sender();
	}
}

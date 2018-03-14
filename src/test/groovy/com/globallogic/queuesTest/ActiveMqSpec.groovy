package com.globallogic.queuesTest

import javax.jms.Connection
import javax.jms.Destination
import javax.jms.Message
import javax.jms.MessageConsumer
import javax.jms.MessageProducer
import javax.jms.DeliveryMode
import javax.jms.Session
import javax.jms.TextMessage
import org.apache.activemq.ActiveMQConnectionFactory
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner

class ActiveMqSpec extends Specification {
	
	Session session;
	Connection connection;
	MessageConsumer consumer;
	MessageProducer producer;

    def "Testing the activemq consumer"() {
        given: "prepare the activemq message"		
       	def message = "Hello world!"
		prepareActiveMq()		
		// Create message
		String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode()
		TextMessage tm = session.createTextMessage(text)

		when: "produce the message - send it to the queue"
		System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName())
		producer.send(tm)
		
	    then: "check the message was consumed"
        //message shouldnt be available - it has been already consumed by the Receiver class
		Message nonexistentMessage = consumer.receive(1000);
	    assert nonexistentMessage == null
		
		cleanup: "close the session and connection"
		session.close()
		connection.close()
		producer.close()
    }
	
	def prepareActiveMq() {		
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
	
		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();
		
		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("helloworld.q")

		// Create a MessageProducer from the Session to the Topic or Queue
		producer = session.createProducer(destination)
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)		
		consumer = session.createConsumer(destination)

	}		
}

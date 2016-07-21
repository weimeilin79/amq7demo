package com.redhat.demo.onlineshopping.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.redhat.demo.onlineshopping.model.Booking;

public class JMSConnector {
	
	InitialContext initialContext = null;
	Connection connection0 = null;
	ConnectionFactory cf0=null;
    
    MessageProducer producer=null;
    Session session0=null;
	
	public JMSConnector(){
		

	      try {
	         // Step 1. Look-up the JMS Queue object from JNDI
	         Queue queue = ActiveMQJMSClient.createQueue("ticketQueue");

	         // Step 2. Look-up a JMS Connection Factory object from JNDI on server 0
	         cf0 = new ActiveMQConnectionFactory("tcp://localhost:61616");

	         // Step 3. We create a JMS Connection connection0 which is a connection to server 0
	         connection0 = cf0.createConnection();
	         //connection0.setClientID("admin");
	         
	         // Step 4. We create a JMS Session on server 0, note the session is CLIENT_ACKNOWLEDGE
	         session0 = connection0.createSession(false, Session.CLIENT_ACKNOWLEDGE);

	         // Step 5. We start the connections to ensure delivery occurs on them
	         connection0.start();

	         // Step 6. We create a JMS MessageProducer object on server 0
	         producer = session0.createProducer(queue);

	      }catch(Exception ex){
	    	  ex.printStackTrace();
	    	  System.out.println(ex.getMessage());
	      }
	     
	}
	
	public void sendBooking(Booking booking){
		ObjectMessage message;
		try {
			message = session0.createObjectMessage( booking );
			System.out.println(booking);
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	      
	}

}

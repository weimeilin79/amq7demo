package com.redhat.demo.onlineshopping.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQObjectMessage;

import com.redhat.demo.onlineshopping.model.Booking;

public class JMSConnector {
	
	InitialContext initialContext = null;
	Connection connection0 = null;
	ConnectionFactory cf0=null;
    
	MessageConsumer consumer0=null;
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

	         // Step 6. We create JMS MessageConsumer objects on server 0 and server 1
	         consumer0 = session0.createConsumer(queue);

	         

	      }catch(Exception ex){
	    	  
	    	  ex.printStackTrace();
	      }
	     
	}
	
	public Booking consumeBooking(){
		Booking bookingReceived = null;
		
		try {
			ActiveMQObjectMessage message =(ActiveMQObjectMessage)consumer0.receive(3000);
			
			if(message != null)
				bookingReceived = (Booking)message.getObject();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return bookingReceived;
	      
	}

}

package com.redhat.demo.onlineshopping.example;

import com.redhat.demo.onlineshopping.model.Booking;



public class BookReserve {
	
	   public static void main(final String[] args) throws Exception {
		   System.out.println("===========STARTS!===========");
		   
		   JMSConnector jmsConnector = new JMSConnector();
		   while(true){
			   Booking bookingReceived = jmsConnector.consumeBooking();
			   
			   if(bookingReceived != null){
				   System.out.println("Got message: " + bookingReceived );
			   }
			   
			   
		   }
		   //System.out.println("===========ENDs!===========");  
	   }
	   

		   
}

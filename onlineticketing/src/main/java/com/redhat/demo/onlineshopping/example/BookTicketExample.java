package com.redhat.demo.onlineshopping.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.redhat.demo.onlineshopping.model.Booking;



public class BookTicketExample {
	
	   public static void main(final String[] args) throws Exception {
		   BookTicketExample bookingExample = new BookTicketExample();
		   List<Booking> bookings = bookingExample.randomGenerateBooking();
		   System.out.println("===========STARTS!===========");
		   
		   JMSConnector jmsConnector = new JMSConnector();
		   
		   for(Booking booking: bookings){
			   jmsConnector.sendBooking(booking);
		   }
		   System.out.println("===========ENDs!===========");  
	   }
	   
	   private List<Booking> randomGenerateBooking(){
		   
		   List<Booking> bookings= new ArrayList<Booking>();
		   Random rand = new Random();
		   int bookingNumbers = rand.nextInt((1000 - 1) + 1) + 1;
		   
		   for(int i=0; i<bookingNumbers; i++ ){
			   Booking booking = new Booking();
			   booking.setName(randomStringName());
			   booking.setZone(randomZone());
			   booking.setNumberofseats(randomSeats());
			   
			   bookings.add(booking);
		   }
		   
		   return bookings;
	   }
	   
	   private String randomStringName(){
		   String name ="";
		   int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (new Random().nextFloat() * (rightLimit - leftLimit));
		        buffer.append((char) randomLimitedInt);
		    }
		    name = buffer.toString();
		   
		   return name;
	   }
	   
	   private Integer randomZone(){
		   
		   Random rand = new Random();
		   int zoneNumber = rand.nextInt((4 - 1) + 1) + 1;
		   
		   return zoneNumber;
	   }
	   
	   private Integer randomSeats(){
		   
		   Random rand = new Random();
		   int zoneNumber = rand.nextInt((10 - 1) + 1) + 1;
		   
		   return zoneNumber;
	   }

		   
}

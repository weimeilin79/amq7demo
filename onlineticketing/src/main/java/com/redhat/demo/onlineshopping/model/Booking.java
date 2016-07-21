package com.redhat.demo.onlineshopping.model;

import java.io.Serializable;


public class Booking implements Serializable{
		
	
	private static final long serialVersionUID = 1928533940834321707L;
	private String name;
	private Integer zone;
	private int numberofseats;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getZone() {
		return zone;
	}
	public void setZone(Integer zone) {
		this.zone = zone;
	}
	public int getNumberofseats() {
		return numberofseats;
	}
	public void setNumberofseats(int numberofseats) {
		this.numberofseats = numberofseats;
	} 
	
	@Override
	public String toString(){
		return "Booking name:["+name+"] zone:["+zone+"] number of seats:["+numberofseats+"]";
	}

	
}

package com.example.testh2.model;

public class AddressWithNoID {

	private String street;
	private String village;
	private String zip;
	
	
	public AddressWithNoID() {

	}
	
	public AddressWithNoID(String street, String village, String zip) {
		this.street = street;
		this.village = village;
		this.zip = zip;
	}
	
	
	
	@Override
	public String toString() {
		return "Address [street=" + street 
				+ ", region=" + village	+ ", zip=" + zip + "]";
	}
		
		
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}	
}

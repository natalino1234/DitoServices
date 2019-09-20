package com.dito.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TimelineEvent implements Serializable,Comparable<TimelineEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String timestamp;
	
	private double revenue;
	
	private String transaction;
	
	private String store_name;
	
	private List<Product> products = new ArrayList<Product>();;
	
	
	public void setTimestamp(String timestamp) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
		System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.err.println(sdf.parse(timestamp));
		System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		this.timestamp = timestamp;
	}
	
	public String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
		return timestamp;
	}
	
	/*public Date DateTimestamp() {
		return timestamp;
	}*/

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/*public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}*/

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TimelineEvent) {
			TimelineEvent event = (TimelineEvent) obj;
			return (this.getTimestamp().equals(event.getTimestamp()) &&
			   this.getRevenue()==event.getRevenue() &&
			   this.getStore_name().equals(event.getStore_name()) &&
			   this.getProducts()==event.getProducts());
		}
		return false;
	}

	@Override
	public String toString() {
		return "TimelineEvent [timestamp=" + timestamp + ", revenue=" + revenue + ", transaction=" + transaction
				+ ", store_name=" + store_name + ", products=" + products + "]";
	}

	@Override
	public int compareTo(TimelineEvent other) {
//		if(this.DateTimestamp().before(other.DateTimestamp())) {
//			return -1;
//		}else if(this.DateTimestamp().after(other.DateTimestamp())) {
//			return 1;
//		}
//		return 0;
		return this.timestamp.compareTo(other.getTimestamp())*-1;
	}

	
	
}

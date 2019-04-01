package com.example.cityspringboot.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_REST")
public class Rest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id")
	int id;
	
	@Column(name = "person_name")
	String personName;
	
	public int tripID;
	
	public String tripName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	Trip trip;

	
	public Rest() {
		
	}
	

	public Rest(int id, String personName, String tripName) {
		this.id = id;
		this.personName = personName;
		this.tripName = tripName;
	}
	
	public Rest(String personName, int tripID) {
		this.personName = personName;
		this.tripID = tripID;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public int getTripID() {
		return tripID;
	}


	public void setTripID(int tripID) {
		this.tripID = tripID;
	}


	public String getTripName() {
		return tripName;
	}


	public void setTripName(String tripName) {
		this.tripName = tripName;
	}


	public Trip getTrip() {
		return trip;
	}


	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
		
}
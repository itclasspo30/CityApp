package com.example.cityspringboot.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSPORT")
public class Transport {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
	int id;
	
	@Column(name = "transport_name")
	String name;
	
	@Column(name = "seats")
	int seats;
	
	@OneToMany(mappedBy = "transport")
    List<Trip> trips = new ArrayList<>();
	
	public Transport() {
    }
	
    public Transport(String name, int seats) {
        
        this.name = name;
        this.seats = seats;
    }
    
    public Transport(int id, String name, int seats) {
        
        this.id = id;
	    this.name = name;
        this.seats = seats;
    }
	
	
	
	public void addTrip(Trip trip) {
        trip.setTransport(this);
        trips.add(trip);
    }

    public void remove(Trip trip) {
        trips.remove(trip);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	
	
	

}

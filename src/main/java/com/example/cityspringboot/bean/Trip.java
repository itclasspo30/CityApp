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
@Table(name = "TRIPS")
public class Trip {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
	int id;
	
	@Column(name = "trip_name")
	String name;
	
	
	int cityID;
	
	
	int transportID;
	
	public String cityName;
	
	public String transportName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transport_id")
	Transport transport;
	
	
	public Trip() {
		
	}

	
	public Trip (String name, City city, Transport transport) {
		this.name = name;
		this.city = city;
		this.transport = transport;
	}
	
	public Trip (int id, String name, String cityName, String transportName) {
		this.id = id;
		this.name = name;
		this.cityName = cityName;
		this.transportName = transportName;
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
	
	
	
	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	
	public int getTransportID() {
		return transportID;
	}

	public void setTransportID(int transportID) {
		this.transportID = transportID;
	}
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", name=" + name + ", cityName=" + cityName + ", transportName=" + transportName
				+ "]";
	}
	
	
	
	

}

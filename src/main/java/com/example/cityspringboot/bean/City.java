package com.example.cityspringboot.bean;


import java.io.Serializable;
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
@Table(name = "CITIES")
public class City implements Serializable{
    
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;
    
    @Column(name = "city_name")
    private String name;
    
    @Column(name = "population")
    private int population;
   
    @OneToMany(mappedBy = "city")
    List<Trip> trips = new ArrayList<>();

    public City() {
    }

    public City(String name, int population) {
        
        this.name = name;
        this.population = population;
    }
    
    public City(int id, String name, int population) {
        
        this.id = id;
	    this.name = name;
        this.population = population;
    }
   
    public void addTrip(Trip trip) {
        trip.setCity(this);
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}

package com.example.cityspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.cityspringboot.bean.City;
import com.example.cityspringboot.bean.Transport;
import com.example.cityspringboot.bean.Trip;

@Service
public class TripService implements IWorkBaseService<Trip> {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    IWorkBaseService<City> sityService;
    @Autowired
    IWorkBaseService<Transport> transportService;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM trips WHERE trip_id = ?", id);
    	String targetName = (String) targetMap.get("trip_name");
        return targetName;
    }
    

    @Override
    public List<Trip> findAll() {
        List<Map<String, Object>> mapedTrips = jdbcTemplate.queryForList("SELECT * FROM trips ORDER BY trip_id");
    	List<Trip> trips = new ArrayList<Trip>();
    	
    	for (Map<String, Object> mapedTrip : mapedTrips) {
    		String cityName = sityService.findNameById((int)mapedTrip.get("city_id"));
    		String transportName = transportService.findNameById((int)mapedTrip.get("transport_id"));
    		Trip newTrip = new Trip((int)mapedTrip.get("trip_id"), (String)mapedTrip.get("trip_name"), cityName, transportName);
    		trips.add(newTrip);
    	}	
    	return trips;
    }
    
   
    @Override
    public Trip addNew(Trip newTrip) {
    	jdbcTemplate.update("INSERT INTO trips (trip_name, city_id, transport_id) VALUES (?, ?, ?)", newTrip.getName(), newTrip.getCityID(), newTrip.getTransportID());
    	return newTrip;
    }
    
    
    @Override
    public long deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM trips WHERE trip_id = ?", id);
    	return id;
    }
    
    
    @Override
    public int updateById(Trip trip) {
    	return jdbcTemplate.update("UPDATE trips SET trip_name = ?, city_id = ?, transport_id = ? WHERE trip_id = ?", trip.getName(), trip.getCityID(), trip.getTransportID(), trip.getId());
    }
    
}
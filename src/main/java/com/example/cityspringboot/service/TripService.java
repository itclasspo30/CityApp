package com.example.cityspringboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TripService implements ITripService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM trips WHERE trip_id = ?", id);
    	String targetName = (String) targetMap.get("trip_name");
        return targetName;
    }
    

    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM trips ORDER BY trip_id");
    }
    
   
    @Override
    public String addTrip(String name, int cityID, int transportID) {
    	jdbcTemplate.update("INSERT INTO trips (trip_name, city_id, transport_id) VALUES (?, ?, ?)", name, cityID, transportID);
    	return "redirect:/showTrips";
    }
    
    
    @Override
    public String deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM trips WHERE trip_id = ?", id);
    	return "redirect:/showTrips";
    }
    
    
    @Override
    public String updateById(long id, String name, int cityID, int transportID) {
    	
    	jdbcTemplate.update("UPDATE trips SET trip_name = ?, city_id = ?, transport_id = ? WHERE trip_id = ?", name, cityID, transportID, id);
    	return "redirect:/showTrips";
    }
    
}
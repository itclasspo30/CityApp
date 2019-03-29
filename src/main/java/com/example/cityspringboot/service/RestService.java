package com.example.cityspringboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RestService implements IRestService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM person_rest ORDER BY rest_id");
    }
    
   
    @Override
    public String addRest(String personName, int tripID) {
    	jdbcTemplate.update("INSERT INTO person_rest (person_name, trip_id) VALUES (?, ?)", personName, tripID);
    	return "redirect:/showRest";
    }
    
    
    @Override
    public String deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM person_rest WHERE rest_id = ?", id);
    	return "redirect:/showRest";
    }
    
    
    @Override
    public String updateById(long id, String personName, int tripID) {
    	
    	jdbcTemplate.update("UPDATE person_rest SET person_name = ?, trip_id = ? WHERE rest_id = ?", personName, tripID, id);
    	return "redirect:/showTrips";
    }
    
}
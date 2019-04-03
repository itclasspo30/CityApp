package com.example.cityspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.cityspringboot.bean.Rest;
import com.example.cityspringboot.bean.Trip;

@Service
public class RestService implements IWorkBaseService<Rest> {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    IWorkBaseService<Trip> tripService;

    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM person_rest WHERE rest_id = ?", id);
    	String targetName = (String) targetMap.get("person_name");
        return targetName;
    }
    
    
    
    @Override
    public List<Rest> findAll() {
    	
    	List<Map<String, Object>> mapedRest = jdbcTemplate.queryForList("SELECT * FROM person_rest ORDER BY rest_id");
    	List<Rest> restList = new ArrayList<Rest>();
    	
    	for (Map<String, Object> mapedSingleRest : mapedRest) {    		
    		String tripName = tripService.findNameById((int)mapedSingleRest.get("trip_id"));
    		Rest newRest = new Rest((int)mapedSingleRest.get("rest_id"), (String)mapedSingleRest.get("person_name"), tripName);
    		restList.add(newRest);
    	}
        return restList;
    }
    
   
    @Override
    public Rest addNew(Rest newRest) {
    	jdbcTemplate.update("INSERT INTO person_rest (person_name, trip_id) VALUES (?, ?)", newRest.getPersonName(), newRest.getTripID());
    	return newRest;
    }
    
    
    @Override
    public long deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM person_rest WHERE rest_id = ?", id);
    	return id;
    }
    
    
    @Override
    public int updateById(Rest rest) {
    	return jdbcTemplate.update("UPDATE person_rest SET person_name = ?, trip_id = ? WHERE rest_id = ?", rest.getPersonName(), rest.getTripID(), rest.getId());
    }
    
}
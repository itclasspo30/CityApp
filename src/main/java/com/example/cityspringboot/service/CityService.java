package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.City;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM cities WHERE city_id = ?", id);
    	String targetName = (String) targetMap.get("city_name");
        return targetName;
    }
    
    
    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM cities ORDER BY city_id");
    }
    
    
    @Override
    public String addCity(City newCity) {
    	jdbcTemplate.update("INSERT INTO cities (city_name, population) VALUES (?, ?)", newCity.getName(), newCity.getPopulation());
    	return "redirect:/showCities";
    }
    
    
    @Override
    public String deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM cities WHERE city_id = ?", id);
    	return "redirect:/showCities";
    }
    
    
    @Override
    public String updateById(long id, String name, int population) {
    	jdbcTemplate.update("UPDATE cities SET city_name = ?, population = ? WHERE city_id = ?", name, population, id);
    	return "redirect:/showCities";
    }
    
}
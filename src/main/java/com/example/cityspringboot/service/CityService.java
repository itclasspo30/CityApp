package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CityService implements IWorkBaseService<City> {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM cities WHERE city_id = ?", id);
    	String targetName = (String) targetMap.get("city_name");
        return targetName;
    }
    
    
    @Override
    public List<City> findAll() {
    	List<City> cities = new ArrayList<City>();
    	List<Map<String, Object>> mapedCities = jdbcTemplate.queryForList("SELECT * FROM cities ORDER BY city_id");
    	for (Map<String, Object> mapedCity : mapedCities) {
    		cities.add(new City((int)mapedCity.get("city_id"), (String)mapedCity.get("city_name"), (int)mapedCity.get("population")));
    	}
        return cities;
    }
    
    
    @Override
    public City addNew(City newCity) {
    	jdbcTemplate.update("INSERT INTO cities (city_name, population) VALUES (?, ?)", newCity.getName(), newCity.getPopulation());
    	return newCity;
    }
    
    
    @Override
    public long deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM cities WHERE city_id = ?", id);
    	return id;
    }
    
    
    @Override
    public int updateById(City city) {
    	return jdbcTemplate.update("UPDATE cities SET city_name = ?, population = ? WHERE city_id = ?", city.getName(), city.getPopulation(), city.getId());
    }
    
    
    
    
}
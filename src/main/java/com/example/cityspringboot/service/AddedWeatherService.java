package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.Weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddedWeatherService implements IAddedWeatherService<Weather> {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public List<Weather> findByDate(String date) {
    	List<Weather> weatherList = new ArrayList<Weather>();
    	List<Map<String, Object>> mapedWeather = jdbcTemplate.queryForList("SELECT * FROM weather WHERE date = ?::date ORDER BY id", date);
    	String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
    	for (Map<String, Object> mapedSingleWeather : mapedWeather) {
    		weatherList.add(new Weather((int)mapedSingleWeather.get("id"), (String)df.format((Date)mapedSingleWeather.get("date")), (double)mapedSingleWeather.get("temp")));
    	}
    	
        return weatherList;
    }
    
    
    
    @Override
    public List<Weather> findAll() {
    	List<Weather> weatherList = new ArrayList<Weather>();
    	List<Map<String, Object>> mapedWeather = jdbcTemplate.queryForList("SELECT * FROM weather ORDER BY id");
    	String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
    	for (Map<String, Object> mapedSingleWeather : mapedWeather) {
    		weatherList.add(new Weather((int)mapedSingleWeather.get("id"), (String)df.format((Date)mapedSingleWeather.get("date")), (double)mapedSingleWeather.get("temp")));
    	}
    	
        return weatherList;
    }
    
    
    @Override
    public Weather addNew(Weather newWeather) {
    	jdbcTemplate.update("INSERT INTO weather (date, temp) VALUES (?::date, ?)", newWeather.getDate(), newWeather.getTemp());
    	return newWeather;
    }
    
    
    @Override
    public long deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM weather WHERE id = ?", id);
    	return id;
    }
    
    
    @Override
    public int updateById(Weather weather) {
    	return jdbcTemplate.update("UPDATE weather SET date = ?::date, temp = ? WHERE id = ?", weather.getDate(), weather.getTemp(), weather.getId());
    }
    
}
package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.City;
import java.util.List;

public interface ICityService {

    public List<City> findAll();

	public String deleteById(long id);
	
	public String addCity(City newCity);
	
	public City updateById(long id, String name, int population);
}
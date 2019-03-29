package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.City;
import java.util.List;
import java.util.Map;

public interface ICityService {

	public String findNameById(int id);
	public List<Map<String, Object>> findAll();
	public String deleteById(long id);
	public String addCity(City newCity);
	public String updateById(long id, String name, int population);
}
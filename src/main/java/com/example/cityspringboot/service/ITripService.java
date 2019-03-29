package com.example.cityspringboot.service;

import java.util.List;
import java.util.Map;

public interface ITripService {

	public String findNameById(int id);
	public List<Map<String, Object>> findAll();
	public String deleteById(long id);
	public String addTrip(String name, int cityID, int transportID);
	public String updateById(long id, String name, int city_id, int transport_id);
}
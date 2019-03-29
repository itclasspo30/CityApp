package com.example.cityspringboot.service;

import java.util.List;
import java.util.Map;

public interface IRestService {

    public List<Map<String, Object>> findAll();
	public String deleteById(long id);
	public String addRest(String personName, int tripID);
	public String updateById(long id, String personName, int tripID);
}
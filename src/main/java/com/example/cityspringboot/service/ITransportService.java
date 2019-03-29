package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.Transport;

import java.util.List;
import java.util.Map;

public interface ITransportService {

	public String findNameById(int id);
	public List<Map<String, Object>> findAll();
	public String deleteById(long id);
	public String addTransport(Transport newTransport);
	public String updateById(long id, String name, int seats);
}
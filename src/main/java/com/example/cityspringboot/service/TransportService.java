package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.Transport;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportService implements ITransportService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM transport WHERE transport_id = ?", id);
    	String targetName = (String) targetMap.get("transport_name");
        return targetName;
    }
    

    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM transport ORDER BY transport_id");
    }
    
    
    @Override
    public String addTransport(Transport newTransport) {
    	jdbcTemplate.update("INSERT INTO transport (transport_name, seats) VALUES (?, ?)", newTransport.getName(), newTransport.getSeats());
    	return "redirect:/showCities";
    }
    
    
    @Override
    public String deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM transport WHERE transport_id = ?", id);
    	return "redirect:/showCities";
    }
    
    
    @Override
    public String updateById(long id, String name, int seats) {
    	jdbcTemplate.update("UPDATE transport SET transport_name = ?, seats = ? WHERE transport_id = ?", name, seats, id);
    	return "redirect:/showCities";
    }
    
}
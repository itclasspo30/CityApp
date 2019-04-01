package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportService implements IMainService<Transport> {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    public String findNameById(int id) {
    	Map<String, Object> targetMap = jdbcTemplate.queryForMap("SELECT * FROM transport WHERE transport_id = ?", id);
    	String targetName = (String) targetMap.get("transport_name");
        return targetName;
    }
    

    @Override
    public List<Transport> findAll() {
        List<Map<String, Object>> mapedTransport = jdbcTemplate.queryForList("SELECT * FROM transport ORDER BY transport_id");
    	List<Transport> transport = new ArrayList<Transport>();
    	for (Map<String, Object> mapedTr : mapedTransport) {
    		transport.add(new Transport((int)mapedTr.get("transport_id"), (String)mapedTr.get("transport_name"), (int)mapedTr.get("seats")));
    	}	
    	return transport;
    }
    
    
    @Override
    public Transport addNew(Transport newTransport) {
    	jdbcTemplate.update("INSERT INTO transport (transport_name, seats) VALUES (?, ?)", newTransport.getName(), newTransport.getSeats());
    	return newTransport;
    }
    
    
    @Override
    public long deleteById(long id) {
    	jdbcTemplate.update("DELETE FROM transport WHERE transport_id = ?", id);
    	return id;
    }
    
    
    @Override
    public int updateById(Transport transport) {
    	return jdbcTemplate.update("UPDATE transport SET transport_name = ?, seats = ? WHERE transport_id = ?", transport.getName(), transport.getSeats(), transport.getId());
    }
    
}
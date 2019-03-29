package com.example.cityspringboot.controller;


import com.example.cityspringboot.bean.Transport;
import com.example.cityspringboot.service.ITransportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransportController {
    
    @Autowired
    ITransportService transportService;
    
        
    @RequestMapping("/showTransport")
    public String findCities(Model model) {
    	
    	List<Map<String, Object>> mapedTransport = (List<Map<String, Object>>) transportService.findAll();
    	List<Transport> transport = new ArrayList<Transport>();
    	
    	for (Map<String, Object> mapedTr : mapedTransport) {
    		transport.add(new Transport((int)mapedTr.get("transport_id"), (String)mapedTr.get("transport_name"), (int)mapedTr.get("seats")));
    	}	
        model.addAttribute("transport", transport);
        
        return "showTransport";
    }
    
    
    
    @RequestMapping(value = { "/addTransport" }, method = RequestMethod.GET)
    public String showAddTransportPage(Model model) {
 
        Transport transport = new Transport();
        model.addAttribute("transport", transport);
 
        return "addTransport";
    }
    
    
    
    @RequestMapping(value = { "/addTransport" }, method = RequestMethod.POST)
    public String saveTransport(Model model, @ModelAttribute("transport") Transport transport) {
 
    	String name = transport.getName();
        int seats = transport.getSeats();
 
        if (name != null && name.length() > 0 && seats >0) {
            Transport newTransport = new Transport(name, seats);
            transportService.addTransport(newTransport);
        }
        return "redirect:/showTransport";
    }
    
   
    
    @RequestMapping(value = {"/delTransport"}, method = RequestMethod.GET)
    public String showDelTransportPage(Model model) {
    	
    	Transport transport = new Transport();
    	model.addAttribute("transport", transport);
    	
    	return "delTransport";
    }
    
    
    
    @RequestMapping(value = {"/delTransport"}, method = RequestMethod.POST)
    public String showDelTransportPage(Model model, @ModelAttribute("transport") Transport transport) {
    	
    	long id = transport.getId();
    	transportService.deleteById(id);
    	
    	return "redirect:/showTransport";
    }
    
    
    
    @RequestMapping(value = { "/updateTransport" }, method = RequestMethod.GET)
    public String showUpdateTransportPage(Model model) {
 
        Transport transport = new Transport();
        model.addAttribute("transport", transport);
 
        return "updateTransport";
    }
    
    
    
    @RequestMapping(value = { "/updateTransport" }, method = RequestMethod.POST)
    public String updateTransport(Model model, @ModelAttribute("transport") Transport transport) {
 
    	long id = transport.getId();
    	String name = transport.getName();
        int seats = transport.getSeats();
 
        if (name != null && name.length() > 0 && seats >0 && id > 0) {
            transportService.updateById(id, name, seats);
        }
        return "redirect:/showTransport";
    
    }
   
}


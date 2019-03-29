package com.example.cityspringboot.controller;


import com.example.cityspringboot.bean.Trip;
import com.example.cityspringboot.service.ICityService;
import com.example.cityspringboot.service.ITransportService;
import com.example.cityspringboot.service.ITripService;

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
public class TripController {  
    
    @Autowired
    ITripService tripService;
    @Autowired
    ICityService sityService;
    @Autowired
    ITransportService transportService;
    
        
    @RequestMapping("/showTrips")
    public String findCities(Model model) {
    	
    	List<Map<String, Object>> mapedTrips = (List<Map<String, Object>>) tripService.findAll();
    	List<Trip> trips = new ArrayList<Trip>();
    	
    	for (Map<String, Object> mapedTrip : mapedTrips) {
    		String cityName = sityService.findNameById((int)mapedTrip.get("city_id"));
    		String transportName = transportService.findNameById((int)mapedTrip.get("transport_id"));
    		Trip newTrip = new Trip((int)mapedTrip.get("trip_id"), (String)mapedTrip.get("trip_name"), cityName, transportName);
    		trips.add(newTrip);
    	}	
    	
    	/*
    	//---Checking the List---
    	for (Trip trip : trips) {
    		System.out.println(trip.toString());
    	}
    	*/   	
    	
        model.addAttribute("trips", trips);
        return "showTrips";
    }
    
    
    
    @RequestMapping(value = { "/addTrip" }, method = RequestMethod.GET)
    public String showAddTripPage(Model model) {
 
        Trip trip = new Trip();
        model.addAttribute("trip", trip);
 
        return "addTrip";
    }
    
    
    
    @RequestMapping(value = { "/addTrip" }, method = RequestMethod.POST)
    public String saveTrip(Model model, @ModelAttribute("trip") Trip trip) {
 
    	String name = trip.getName();
        int cityID = trip.getCityID();
        int transportID = trip.getTransportID();
 
        if (name != null && name.length() > 0 && cityID >0 && transportID>0) {
            tripService.addTrip(name, cityID, transportID);
        }
        return "redirect:/showTrips";
    }
    
    
    
    @RequestMapping(value = {"/delTrip"}, method = RequestMethod.GET)
    public String showDelTripPage(Model model) {
    	
    	Trip trip = new Trip();
    	model.addAttribute("trip", trip);
    	
    	return "delTrip";
    }
    
    
    
    @RequestMapping(value = {"/delTrip"}, method = RequestMethod.POST)
    public String DelTrip(Model model, @ModelAttribute("trip") Trip trip) {
    	
    	long id = trip.getId();
    	tripService.deleteById(id);
    	
    	return "redirect:/showTrips";
    }
    
    
    
    @RequestMapping(value = { "/updateTrip" }, method = RequestMethod.GET)
    public String showUpdateTripPage(Model model) {
 
        Trip trip = new Trip();
        model.addAttribute("trip", trip);
 
        return "updateTrip";
    }
    
    
    
    @RequestMapping(value = { "/updateTrip" }, method = RequestMethod.POST)
    public String updateTrip(Model model, @ModelAttribute("trip") Trip trip) {
 
    	long id = trip.getId();
    	String name = trip.getName();
        int cityID = trip.getCityID();
        int transportID = trip.getTransportID();
 
        if (name != null && name.length() > 0 && cityID >0 && transportID>0) {
            tripService.updateById(id, name, cityID, transportID);
        }
        return "redirect:/showTrips";
    
    }
    
}


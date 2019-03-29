package com.example.cityspringboot.controller;

import com.example.cityspringboot.bean.City;
import com.example.cityspringboot.service.ICityService;

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
public class CityController {
    
    @Autowired
    ICityService cityService;
    
        
    @RequestMapping("/showCities")
    public String findCities(Model model) {
    	
    	List<Map<String, Object>> mapedCities = (List<Map<String, Object>>) cityService.findAll();
    	List<City> cities = new ArrayList<City>();
    	
    	for (Map<String, Object> mapedCity : mapedCities) {
    		cities.add(new City((int)mapedCity.get("city_id"), (String)mapedCity.get("city_name"), (int)mapedCity.get("population")));
    	}
        model.addAttribute("cities", cities);
        
        return "showCities";
    }
    
    
    
    @RequestMapping(value = { "/addCity" }, method = RequestMethod.GET)
    public String showAddCityPage(Model model) {
 
        City city = new City();
        model.addAttribute("city", city);
 
        return "addCity";
    }
    
    
    
    @RequestMapping(value = { "/addCity" }, method = RequestMethod.POST)
    public String saveCity(Model model, @ModelAttribute("city") City city) {
 
    	String name = city.getName();
        int population = city.getPopulation();
 
        if (name != null && name.length() > 0 && population >0) {
            City newCity = new City(name, population);
            cityService.addCity(newCity);
        }
        return "redirect:/showCities";
    }
    
    
    
    @RequestMapping(value = {"/delCity"}, method = RequestMethod.GET)
    public String showDelCityPage(Model model) {
    	
    	City city = new City();
    	model.addAttribute("city", city);
    	
    	return "delCity";
    }
    
    
    
    @RequestMapping(value = {"/delCity"}, method = RequestMethod.POST)
    public String showDelCityPage(Model model, @ModelAttribute("city") City city) {
    	
    	long id = city.getId();
    	cityService.deleteById(id);
    	
    	return "redirect:/showCities";
    }
    
    
    
    @RequestMapping(value = { "/updateCity" }, method = RequestMethod.GET)
    public String showUpdateCityPage(Model model) {
 
        City city = new City();
        model.addAttribute("city", city);
 
        return "updateCity";
    }
    
    
    
    @RequestMapping(value = { "/updateCity" }, method = RequestMethod.POST)
    public String updateCity(Model model, @ModelAttribute("city") City city) {
 
    	long id = city.getId();
    	String name = city.getName();
        int population = city.getPopulation();
 
        if (name != null && name.length() > 0 && population >0 && id > 0) {
            cityService.updateById(id, name, population);
        }
        return "redirect:/showCities";
    
    }
    
}


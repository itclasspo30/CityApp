package com.example.cityspringboot.controller;

import com.example.cityspringboot.bean.City;
import com.example.cityspringboot.bean.CityForm;
import com.example.cityspringboot.service.ICityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
    
    @Autowired
    ICityService cityService;
    
        
    @RequestMapping("/showCities")
    public String findCities(Model model) {
        
        List<City> cities = (List<City>) cityService.findAll();
        model.addAttribute("cities", cities);
        
        return "showCities";
    }
    
    
    
    @RequestMapping(value = { "/addCity" }, method = RequestMethod.GET)
    public String showAddCityPage(Model model) {
 
        CityForm cityForm = new CityForm();
        model.addAttribute("cityForm", cityForm);
 
        return "addCity";
    }
    
    
    
    @RequestMapping(value = { "/addCity" }, method = RequestMethod.POST)
    public String saveCity(Model model, @ModelAttribute("cityForm") CityForm cityForm) {
 
    	String name = cityForm.getName();
        int population = cityForm.getPopulation();
 
        if (name != null && name.length() > 0 && population >0) {
            City newCity = new City(name, population);
            cityService.addCity(newCity);
        }
        return "redirect:/showCities";
    }
    
    
    
    @RequestMapping(value = {"/delCity"}, method = RequestMethod.GET)
    public String showDelCityPage(Model model) {
    	
    	CityForm cityForm = new CityForm();
    	model.addAttribute("cityForm", cityForm);
    	
    	return "delCity";
    }
    
    
    
    @RequestMapping(value = {"/delCity"}, method = RequestMethod.POST)
    public String showDelCityPage(Model model, @ModelAttribute("cityForm") CityForm cityForm) {
    	
    	long id = cityForm.getId();
    	cityService.deleteById(id);
    	
    	return "redirect:/showCities";
    }
    
    
    
    @RequestMapping(value = { "/updateCity" }, method = RequestMethod.GET)
    public String showUpdateCityPage(Model model) {
 
        CityForm cityForm = new CityForm();
        model.addAttribute("cityForm", cityForm);
 
        return "updateCity";
    }
    
    
    
    @RequestMapping(value = { "/updateCity" }, method = RequestMethod.POST)
    public String updateCity(Model model, @ModelAttribute("cityForm") CityForm cityForm) {
 
    	long id = cityForm.getId();
    	String name = cityForm.getName();
        int population = cityForm.getPopulation();
 
        if (name != null && name.length() > 0 && population >0 && id > 0) {
            cityService.updateById(id, name, population);
        }
        return "redirect:/showCities";
    
    }
    
}


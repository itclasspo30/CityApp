package com.example.cityspringboot.controller;

import com.example.cityspringboot.bean.Weather;
import com.example.cityspringboot.service.IAddedWeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WeatherController {
    
    @Autowired
    IAddedWeatherService<Weather> weatherService;
    
    
    
    @RequestMapping(value = {"/findWeather"}, method = RequestMethod.GET)
    public String showFindWeatherPage(Model model) {
    	
    	Weather weather = new Weather();
    	model.addAttribute("weather", weather);
    	
    	return "findWeather";
    }
    
    
    
    @RequestMapping(value = {"/findWeather"}, method = RequestMethod.POST)
    public String findWeather(Model model, @ModelAttribute("weather") Weather weather) {
    	
    	model.addAttribute("weatherList", weatherService.findByDate(weather.getDate()));
    	
    	return "findWeather";
    }
    
    
    
    @RequestMapping("/showWeather")
    public String findWeather(Model model) {
        model.addAttribute("weatherList", weatherService.findAll());
        return "showWeather";
    }
    
    
    
    @RequestMapping(value = { "/addWeather" }, method = RequestMethod.GET)
    public String showAddWeatherPage(Model model) {
 
        Weather weather = new Weather();
        model.addAttribute("weather", weather);
 
        return "addWeather";
    }
    
    
    
    @RequestMapping(value = { "/addWeather" }, method = RequestMethod.POST)
    public String saveWeather(Model model, @ModelAttribute("weather") Weather weather) {
 
    	String date = weather.getDate();
    	double temp = weather.getTemp();
 
        if (date != null) {
        	Weather newWeather = new Weather(date, temp);
        	weatherService.addNew(newWeather);
        }
        return "redirect:/showWeather";
    }
    
    
    
    @RequestMapping(value = {"/delWeather"}, method = RequestMethod.GET)
    public String showDelWeatherPage(Model model) {
    	
    	Weather weather = new Weather();
    	model.addAttribute("weather", weather);
    	
    	return "delWeather";
    }
    
    
    
    @RequestMapping(value = {"/delWeather"}, method = RequestMethod.POST)
    public String delWeather(Model model, @ModelAttribute("weather") Weather weather) {
    	
    	long id = weather.getId();
    	weatherService.deleteById(id);
    	
    	return "redirect:/showWeather";
    }
    
    
    
    @RequestMapping(value = { "/updateWeather" }, method = RequestMethod.GET)
    public String showUpdateWeatherPage(Model model) {
 
        Weather weather = new Weather();
        model.addAttribute("weather", weather);
 
        return "updateWeather";
    }
    
    
    
    @RequestMapping(value = { "/updateWeather" }, method = RequestMethod.POST)
    public String updateCity(Model model, @ModelAttribute("weather") Weather weather) {
 
    	int id = weather.getId();
    	String date = weather.getDate();
         
        if (date != null && date.length() > 0 && id > 0) {
        	weatherService.updateById(weather);
        }
        return "redirect:/showWeather";
    
    }
    
}

package com.example.cityspringboot.service;

import com.example.cityspringboot.bean.City;
import com.example.cityspringboot.repository.CityRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;
    

    @Override
    public List<City> findAll() {

        List<City> cities = (List<City>) repository.findAll();
        
        return cities;
    }
    
    
    @Override
    public String addCity(City newCity) {
    	
    	repository.save(newCity);
    	return "redirect:/showCities";
    }
    
    
    @Override
    public String deleteById(long id) {
    	
    	repository.deleteById(id);
    	return "redirect:/showCities";
    	
    }
    
    
    @Override
    public City updateById(long id, String name, int population) {
    	
    	City updateCity = repository.findById(id).get();
    	updateCity.setName(name);
    	updateCity.setPopulation(population);
    	repository.save(updateCity);
    	
    	return updateCity;
    }
    
    
    
}
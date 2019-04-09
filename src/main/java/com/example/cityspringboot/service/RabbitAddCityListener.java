package com.example.cityspringboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cityspringboot.bean.City;

@Component
public class RabbitAddCityListener {
	
	@Autowired
	IMainService<City> cityService;
	
    static final Logger logger = LoggerFactory.getLogger(RabbitAddCityListener.class);
 
    @RabbitListener(queues = "myQueue")
    public void process(City city){
    	System.out.println("City is coming: " + "Name = " + city.getName() + " Population = " + city.getPopulation());
    	
    	if (city.getName() != null && city.getName().length() > 0 && city.getPopulation() > 0) {
            cityService.addNew(city);
        }
    }
 
}
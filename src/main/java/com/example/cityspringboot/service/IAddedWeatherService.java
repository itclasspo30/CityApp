package com.example.cityspringboot.service;

import java.util.List;

import com.example.cityspringboot.bean.Weather;

public interface IAddedWeatherService<T> extends IMainService<T>{
	
	public List<Weather> findByDate(String date);

}

package com.example.cityspringboot.service;

public interface IWorkBaseService<T> extends IMainService<T>{
	
	public String findNameById(int id);

}

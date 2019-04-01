package com.example.cityspringboot.service;

import java.util.List;

public interface IMainService<T> {
	
	public String findNameById(int id);
	public List<T> findAll();
	public long deleteById(long id);
	public T addNew(T t);
	public int updateById(T t);

}

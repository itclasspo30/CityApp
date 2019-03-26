package com.example.cityspringboot.repository;

import com.example.cityspringboot.bean.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
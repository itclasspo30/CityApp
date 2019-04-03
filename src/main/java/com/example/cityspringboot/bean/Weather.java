package com.example.cityspringboot.bean;

public class Weather {
	
	public int id;
	public String date;
	public double temp;
	
	public Weather() {
		
	}
	
	public Weather(String date, double temp) {
		this.date = date;
		this.temp = temp;
	}
	
	public Weather(int id, String date, double temp) {
		this.id = id;
		this.date = date;
		this.temp = temp;
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}

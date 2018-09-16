package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Phone")
@Table(name="Phone")
public class Phone extends Product{
	
	@Column(name = "specification")
	private String make;
	
	@Column(name = "title")
	private String model;
	
	@Column(name = "storage")
	private String storage;
	
	public Phone(String make, String model, String storage){
		this.make = make;
		this.model = model;
		this.storage = storage;
	}
	
	public Phone(){
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}
}

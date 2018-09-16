package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("TV")
@Table(name="TV")
public class TV extends Product{
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "3D", length = 3)
	private boolean threeD;
	
	public TV(String make, String size, String type, Boolean threeD){
		this.make = make;
		this.size = size;
		this.type = type;
		this.threeD = threeD;
	}
	public TV() {
		
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isThreeD() {
		return threeD;
	}

	public void setThreeD(boolean threeD) {
		this.threeD = threeD;
	}


}

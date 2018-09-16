package com.hibernate.core;

import java.util.List;
import java.util.Set;

import com.hibernate.entity.Customer;
import com.hibernate.entity.Phone;
import com.hibernate.entity.Product;
import com.hibernate.entity.TV;
import com.hibernate.model.*;
import com.hibernate.view.AddProduct;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class Controller {
	private Main view;
	private DBConnector connector;
	private Parent parent;

	public Controller(Main main) {
		view = main;
		connector = new DBConnector();
	}

	public void setAddProduct() {
		view.setAddProduct();
	}

	public void addTV(String make, String size, String type, boolean threeD) {
		if(!make.equals("")) {
			saveToDB(new TV(make, size, type, threeD));
			parent = null;
			back();
		}else 
			((AddProduct) parent).setErrorMake();


	}
	
	public <T>void saveToDB(T t) {
		connector.saveToDB(t);
	}
	
	public <T>void saveToDBSet(Set<T> t) {
		connector.saveToDBSet(t);
	}
	
	public void addPhone(String make, String model, String storage) {
		if(!make.equals("") && !model.equals("")) {
			saveToDB(new Phone(make, model, storage));
			parent = null;
			back();
		}
		else {
			if(model.equals(""))
				((AddProduct) parent).setErrorModel();
			if(make.equals(""))
				((AddProduct) parent).setErrorMake();
		}		
	}

	public void quit() {
		view.quit();
	}

	public void setOrderProduct() {	
		view.setOrderProduct();
	}

	public void setFindProduct(int id) {
		view.setFindProduct(connector.getProduct(id));
	}

	public void setDisplayOrder() {
		view.setDisplayOrder();
	}

	public void back() {
		view.setLogPage();
	}

	public void setCurrentView(Parent parent) {
		this.parent = parent;
	}

	public void close() {
		connector.close();
	}

	public List<Product> getProducts() {
		return connector.getProductSet();
	}

	public void delete(Product product) {
		connector.delete(product);
		back();
	}

	public Customer getCustomer(String name, String surname) {
		return connector.getCustomer(name, surname);
	}

	public List<Customer> getCustomers() {
		return connector.getCustomerList();
	}

}

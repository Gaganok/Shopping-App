package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;
import org.hibernate.query.Query;

import com.hibernate.entity.Customer;
import com.hibernate.entity.Item;
import com.hibernate.entity.Order;
import com.hibernate.entity.Product;
import com.hibernate.entity.TV;

public class DBConnector {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public DBConnector(){
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Customer cus = session.get(Customer.class, 1);
		System.out.println();

		transaction.commit();
	}

	public <T>void saveToDB(T product) {
		transaction = session.beginTransaction();
		session.save(product);
		transaction.commit();
	}

	public List<Product> getProductSet() {


		transaction = session.beginTransaction();

		List<Product> products = session.createQuery("from Product").getResultList();

		transaction.commit();

		return products;
	}

	public <T>void saveToDBSet(Set<T> product) {
		transaction = session.beginTransaction();
		for(T t : product)
			session.save(t);

		transaction.commit();
	}

	public void close() {
		session.close();
		sessionFactory.close();
	}

	public Product getProduct(int id) {
		transaction = session.beginTransaction();
		Product product = session.get(Product.class, id);
		transaction.commit();

		return product;
	}

	public void delete(Product product) {
		transaction = session.beginTransaction();

		for(Item i : product.getItem()) {
			Order order = i.getOrder();
			order.getItem().remove(i);
			session.delete(i);
		}

		session.delete(product);

		transaction.commit();
	}

	public Customer getCustomer(String name, String surname) {
		transaction = session.beginTransaction();

		Query query = session.createQuery(
				"FROM Customer c WHERE c.name = '" + name + "' " + 
				"AND c.surname = '" + surname + "' " );
		List<Customer> cust = query.getResultList();
		
		transaction.commit();
		
		if(cust.size() > 0)
			return cust.get(0);
		else 
			return null;
	}

	public List<Customer> getCustomerList() {
		return (List<Customer>) session.createQuery("from Customer").getResultList();
	}
}

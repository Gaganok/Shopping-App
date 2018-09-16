package com.hibernate.view;

import java.util.List;

import com.hibernate.core.Controller;
import com.hibernate.entity.Customer;
import com.hibernate.entity.Item;
import com.hibernate.entity.Order;
import com.hibernate.entity.Phone;
import com.hibernate.entity.TV;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayOrder extends BorderPane {
	private Controller controller;
	private Button back;
	public DisplayOrder(Controller controller){
		this.controller = controller;
		back = new Button("Back");
		back.getStyleClass().addAll("button-option", "red");
		back.setOnAction(e -> controller.back());
		
		HBox backHBox = new HBox(back);
		backHBox.setAlignment(Pos.CENTER);
		backHBox.setPadding(new Insets(15));
		
		setTop(new LabelLogo());
		setCenter(new ScrollPane(new OrderList(controller.getCustomers())));
		setBottom(backHBox);
	}


	public class OrderList extends VBox{
		OrderList(List<Customer> customers){
			for(Customer c : customers) {
				Label customerName = new Label( c.getName() + " " + c.getSurname());
				customerName.setPadding(new Insets(5, 0, 0, 20));
				getChildren().add(customerName);
				for(Order o : c.getOrder()) {
					Label orderId = new Label( "Order ID: " + o.getId());
					orderId.setPadding(new Insets(5, 0, 0, 40));
					getChildren().add(orderId);
					for(Item i : o.getItem()) {
						Label productLabel = null ;
						if( i.getProduct() instanceof TV) {
							TV tv = (TV) i.getProduct();
							productLabel = new Label( "Make: " + tv.getMake() + " Type: " + tv.getType() 
								+ " Size: " + tv.getSize());
						} else if( i.getProduct() instanceof Phone){
							Phone p = (Phone) i.getProduct();
							productLabel = new Label( "Make: " + p.getMake() + " Model: " + p.getModel()
								+ " Storage: " + p.getStorage());
						}
						productLabel.setPadding(new Insets(5, 0, 0, 60));
						getChildren().add(productLabel);
					}
				}		
			}
		}
	}
	
}

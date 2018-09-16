package com.hibernate.view;

import com.hibernate.core.Controller;
import com.hibernate.entity.Customer;
import com.hibernate.entity.Item;
import com.hibernate.entity.Order;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LogPage extends BorderPane{
	private Controller controller;
	private Button addProduct, orderProduct, findProduct, displayOrder, quit;
	private VBox vBox1, vBox2;
	
	public LogPage(Controller controller){
		this.controller = controller;
		
		addProduct = new Button("Add a new Product");
		orderProduct = new Button("Order Products");
		findProduct = new Button("Find a Product");
		displayOrder = new Button("Display all Orders");
		quit = new Button("Quit");
		
		addProduct.getStyleClass().addAll("button-option", "green");
		orderProduct.getStyleClass().addAll("button-option", "green");
		findProduct.getStyleClass().addAll("button-option", "green");
		displayOrder.getStyleClass().addAll("button-option", "green");
		quit.getStyleClass().addAll("button-option", "red");
		
		addProduct.setOnAction(e -> controller.setAddProduct());
		orderProduct.setOnAction(e -> controller.setOrderProduct());
		findProduct.setOnAction(e -> {
			Dialog dialog = new Dialog<>();
			dialog.setTitle("StockApp");
			//dialog.setHeaderText("Fill in the information below.");
			dialog.setResizable(false);
			
			TextField productID = new TextField();
			
			GridPane grid = new GridPane();
			grid.add(new Label("Product ID: "), 1, 1);
			grid.add(productID, 2, 1);
			grid.setHgap(5);
			
			dialog.getDialogPane().setContent(grid);
			
			ButtonType buttonTypeOk = new ButtonType("Find");
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			
			dialog.showAndWait();
			
			if(dialog.getResult() == buttonTypeOk) {
				try {
					int id = Integer.parseInt(productID.getText());
					controller.setFindProduct(id);
				}catch(NumberFormatException exeption) {
					productID.getStylesheets().add("error");
				}
			}	
		});
		displayOrder.setOnAction(e -> controller.setDisplayOrder());
		quit.setOnAction(e -> controller.quit());
		
		getStyleClass().add("white");
		
		vBox1 = new VBox(15, addProduct, orderProduct, findProduct, displayOrder);
		vBox2 = new VBox(25, vBox1, quit);
		
		vBox1.setAlignment(Pos.CENTER);
		vBox2.setAlignment(Pos.CENTER);
		
		setTop(new LabelLogo());
		setCenter(vBox2);
	}
	
}

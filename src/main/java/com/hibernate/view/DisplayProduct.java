package com.hibernate.view;

import com.hibernate.core.Controller;
import com.hibernate.entity.Item;
import com.hibernate.entity.Phone;
import com.hibernate.entity.Product;
import com.hibernate.entity.TV;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayProduct extends BorderPane{
	private Controller controller;
	private Button delete, back;

	public DisplayProduct(Controller controller, Product product){
		this.controller = controller;
		
		delete = new Button("Delete Product");
		delete.setOnAction(e -> controller.delete(product));
		delete.getStyleClass().addAll("red", "button-option");
		
		back = new Button("Back");
		back.setOnAction(e -> controller.back());
		back.getStyleClass().addAll("red", "button-option");
		
		ProductComponent prodComp = new ProductComponent(product);
		prodComp.setPadding(new Insets(150, 0, 0, 0));
		prodComp.getStyleClass().add("label-logo");
		
		HBox hBox = new HBox(20, back, delete);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10, 0, 10, 0));
		
		setTop(new LabelLogo());
		setCenter(prodComp);
		setBottom(hBox);
	}

}

package com.hibernate.view;

import com.hibernate.entity.Phone;
import com.hibernate.entity.Product;
import com.hibernate.entity.TV;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ProductComponent extends HBox {
	private GridPane gridPane;
	
	<T extends Product>ProductComponent(T product){
		gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(5);
		
		setSpacing(55);
		setPadding(new Insets(5, 5, 0, 35));
		setAlignment(Pos.CENTER);
		
		if(product instanceof TV ) {
			TV tv = (TV) product;
			
			gridPane.add(new Label("Make"), 0, 0);
			gridPane.add(new Label(tv.getMake()), 1, 0);
			gridPane.add(new Label("Size"), 0, 1);
			gridPane.add(new Label(tv.getSize()), 1, 1);
			gridPane.add(new Label("Type"), 0, 2);
			gridPane.add(new Label(tv.getType()), 1, 2);
			
			if(tv.isThreeD()) 
				gridPane.add(new Label("3D"), 2, 0);
			
		} else {
			Phone phone = (Phone) product;
			
			gridPane.add(new Label("Make"), 0, 0);
			gridPane.add(new Label(phone.getMake()), 1, 0);
			gridPane.add(new Label("Model"), 0, 1);
			gridPane.add(new Label(phone.getModel()), 1, 1);
			gridPane.add(new Label("Storage"), 0, 2);
			gridPane.add(new Label(phone.getStorage()), 1, 2);
		}
		
		getChildren().add(gridPane);	
	}
}

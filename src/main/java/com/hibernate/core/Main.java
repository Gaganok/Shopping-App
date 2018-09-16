package com.hibernate.core;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Product;
import com.hibernate.model.DBConnector;
import com.hibernate.view.*;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application{
	private final int WIDTH = 640, HEIGHT = 480;
	private Stage primaryStage;
	private Controller controller;
	private LogPage logPage;
	private AddProduct addProduct;
	
	
	public static void main(String[] args) {
		
		//DBConnector dbcon = new DBConnector();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		controller = new Controller(this);
		
		setLogPage();
		
		primaryStage.setOnCloseRequest(e -> controller.close());
		
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void iniGrid(GridPane gridPane ,int column, int row) {
		final double PERCENT_WIDTH = 100 / column;
		final double PERCENT_HEIGHT = 100 / row;

		ObservableList<ColumnConstraints>  columnConstrains = gridPane.getColumnConstraints();
		for(int i = 0; i < column; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(PERCENT_WIDTH);
			columnConstrains.add(colConst);
		}

		ObservableList<RowConstraints>  rowConstrains = gridPane.getRowConstraints();
		for(int i = 0; i < row; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(PERCENT_HEIGHT);
			rowConstrains.add(rowConst);
		}
	}
	
	public void setAddProduct() {
		iniStage(new AddProduct(controller));
	}
	
	public void setLogPage() {
		iniStage(new LogPage(controller));
	}
	
	private void iniStage(Parent a) {
		Scene scene = new Scene(a, WIDTH, HEIGHT);
		scene.getStylesheets().add(getClass().getResource("styleSheet.css").toExternalForm());
		primaryStage.setScene(scene);
	}

	public void setOrderProduct() {
		iniStage(new MakeOrder(controller));
	}

	public void setFindProduct(Product product) {
		iniStage(new DisplayProduct(controller, product));
	}

	public void quit() {
		primaryStage.close();
	}

	public void setDisplayOrder() {
		iniStage(new DisplayOrder(controller));
		
	}
	
}

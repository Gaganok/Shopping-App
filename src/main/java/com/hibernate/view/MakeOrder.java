package com.hibernate.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hibernate.core.Controller;
import com.hibernate.entity.Customer;
import com.hibernate.entity.Item;
import com.hibernate.entity.Order;
import com.hibernate.entity.Phone;
import com.hibernate.entity.Product;
import com.hibernate.entity.TV;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MakeOrder extends BorderPane {

	private Set<Item> itemSet;
	private List<Product> products;
	private Customer customer;
	private Controller controller;
	private Button order, back;
	private SplitPane splitPane;
	private VBox vBasketBox = new VBox(15, new Label("Basket"));

	public MakeOrder(Controller controller) {
		this.controller = controller;
		products = controller.getProducts();
		itemSet = new HashSet<Item>();

		order = new Button("Order");
		order.setOnAction(e -> {
			if(itemSet.size() > 0) {
				toDB();
			}
		});
		order.getStyleClass().addAll("button-submit", "green");

		back = new Button("Back");
		back.setOnAction(e -> controller.back());
		back.getStyleClass().addAll("button-submit", "red");

		VBox vStockBox = new VBox(20);
		vStockBox.setPadding(new Insets(0, 20, 0, 50));

		for(Product p : products)
			vStockBox.getChildren().add(new ProductComponentExtended(p));

		HBox hBox = new HBox(15, back, order);
		hBox.setPadding(new Insets(5, 0, 5, 0));
		hBox.setAlignment(Pos.CENTER);

		setTop(new LabelLogo());	
		setCenter(new ScrollPane(vStockBox));
		setBottom(hBox);

		getStyleClass().add("white");
	}

	private void toDB() {
		Dialog dialog = new Dialog<>();
		dialog.setTitle("StockApp");
		dialog.setHeaderText("Fill in the information below.");
		dialog.setResizable(false);

		TextField name = new TextField();
		name.setPromptText("John");

		TextField surname = new TextField();
		surname.setPromptText("Black");

		ToggleGroup group = new ToggleGroup();
		RadioButton rb1, rb2;

		rb1 = new RadioButton("New");
		rb2 = new RadioButton("Add");
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb1.setSelected(true);

		GridPane grid = new GridPane();
		grid.setHgap(45);

		grid.add(new HBox(20, rb1, rb2), 1, 0);
		grid.add(new Label("Name: "), 1, 1);
		grid.add(name, 2, 1);
		grid.add(new Label("Surname: "), 1, 3);
		grid.add(surname, 2, 3);

		dialog.getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Submit");
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		dialog.showAndWait();

		if(dialog.getResult() == buttonTypeOk) {
			if(!name.getText().equals("") && !surname.getText().equals("")) {
				
				if(((RadioButton) group.getSelectedToggle()).getText().equals("New"))
					customer = new Customer(name.getText(), surname.getText());
				else
					customer = controller.getCustomer(name.getText(), surname.getText());

				if(customer != null) {
					Order order = new Order(customer);
					order.setItem(itemSet);
					customer.addOrder(order);

					for(Item i : itemSet) {
						i.setOrder(order);
						i.getProduct().addItem(i);
					}
					
					controller.saveToDB(customer);
					controller.saveToDB(order);
					controller.saveToDBSet(itemSet);

					controller.back();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("No " + name.getText() + " " + surname.getText() + " found!");
					alert.showAndWait();
				}
			}
		}
	}

	public class ProductComponentExtended extends ProductComponent {
		private Button addOrder, deleteOrder;
		private TextField qty;
			
		<T extends Product>ProductComponentExtended(T product){
			super(product);
			
			qty = new TextField("1");
			qty.setPrefSize(50, 25);

			addOrder = new Button("Add To Order");
			addOrder.setOnAction(e -> {
				if(!getStyleClass().contains("greenish")) {
					itemSet.add(new Item(Integer.parseInt(qty.getText()), product));
					getStyleClass().add("greenish");
					deleteOrder.setVisible(true);
					qty.setDisable(true);
				}
			});

			deleteOrder = new Button("Delete From Order");
			deleteOrder.getStyleClass().add("red");
			deleteOrder.setVisible(false);
			deleteOrder.setOnAction(e -> {
				getStyleClass().remove("greenish");
				deleteOrder.setVisible(false);
				qty.setDisable(false);
			});

			getChildren().addAll(new VBox(10, new HBox(15, new Label("QTY"), qty), new HBox(15, addOrder, deleteOrder)));	
		}
	}
}

package com.hibernate.view;

import com.hibernate.core.Controller;
import com.hibernate.core.Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddProduct extends BorderPane {
	private Controller controller;
	private RadioButton rb1, rb2, rb3;
	private TextField modelT, makeT;
	private CheckBox checkBox;
	private ChoiceBox choiceBox;
	private ComboBox comboBox;
	private Button back, submit;
	private VBox vBox;
	private GridPane gridPane;
	private ToggleGroup group;

	public AddProduct(Controller controller){
		this.controller = controller;
		controller.setCurrentView(this);

		choiceBox = new ChoiceBox(FXCollections.observableArrayList("TV", "Phone"));
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						setSelection(newValue.intValue());
					}	
				});
		back = new Button("Back");
		submit = new Button("Submit");

		back.getStyleClass().addAll("red", "button-option");
		submit.getStyleClass().addAll("green", "button-option");

		back.setOnAction(e -> controller.back());
		submit.setOnAction(e -> {
			resetError();
			if(choiceBox.getSelectionModel().getSelectedIndex() == 0) 
				controller.addTV(makeT.getText(), (String)comboBox.getSelectionModel().getSelectedItem(), 
						((RadioButton) group.getSelectedToggle()).getText(), checkBox.isSelected());
			else
				controller.addPhone(makeT.getText(), modelT.getText(), ((RadioButton) group.getSelectedToggle()).getText());
		});

		choiceBox.getSelectionModel().select(0);
		choiceBox.getStyleClass().add("choice-box");

		setSelection(0);

		HBox hBox = new HBox(20, back, submit);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(20));
		
		setTop(new LabelLogo());
		setBottom(hBox);

		getStyleClass().add("white");
	}

	private void setSelection(int selection) {

		group = new ToggleGroup();

		gridPane = new GridPane();
		makeT = new TextField();
		modelT = new TextField();
		
		String[][] radioInputs = {{"LCD", "LED", "Plasma"}, {"32GB","64GB", "128GB"}};

		rb1 = new RadioButton(radioInputs[selection][0]);
		rb2 = new RadioButton(radioInputs[selection][1]);
		rb3 = new RadioButton(radioInputs[selection][2]);

		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);

		rb1.setSelected(true);

		gridPane.add(new HBox(25, rb1, rb2, rb3), 1, 2);

		if(selection == 0) { //TV
			makeT.setPromptText("LG");

			comboBox = new ComboBox(FXCollections.observableArrayList("32\"", "40\"", "43\"", "50\"", "55\"", "60\"", "65\""));
			comboBox.getSelectionModel().select(0);

			checkBox = new CheckBox();

			gridPane.add(new Label("Make "), 0, 0);
			gridPane.add(makeT, 1, 0);
			gridPane.add(new Label("Size "), 0, 1);
			gridPane.add(comboBox, 1, 1);
			gridPane.add(new Label("Type "), 0, 2);
			gridPane.add(new Label("3D "), 0, 3);
			gridPane.add(checkBox, 1, 3);

		} else { //Phone
			makeT.setPromptText("Apple");
			modelT.setPromptText("IPhone X");

			gridPane.add(new Label("Make "), 0, 0);
			gridPane.add(makeT, 1, 0);
			gridPane.add(new Label("Model "), 0, 1);
			gridPane.add(modelT, 1, 1);
			gridPane.add(new Label("Storage "), 0, 2);
		}
		gridPane.setPadding(new Insets(100, 0, 0, 50));
		gridPane.setVgap(20);
		gridPane.setHgap(20);

		HBox hBox = new HBox(0, choiceBox, gridPane);
		hBox.setAlignment(Pos.CENTER);
		setCenter(hBox);
	}

	public void setErrorModel() {
		modelT.getStyleClass().add("error");
	}

	public void setErrorMake() {
		makeT.getStyleClass().add("error");
	}

	public void resetError() {
		modelT.getStyleClass().remove("error");
		makeT.getStyleClass().remove("error");
	}

}

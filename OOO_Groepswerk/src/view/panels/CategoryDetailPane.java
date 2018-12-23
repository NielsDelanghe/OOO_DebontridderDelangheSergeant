package view.panels;

import controller.DBContext;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Category;

import java.util.ArrayList;

public class CategoryDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox categoryField;
	private Category category;
	private ObservableList<Savable> categoryList;
	private ObservableList<String> categoryTitles = FXCollections.observableArrayList(new ArrayList<>());

	public CategoryDetailPane(ObservableList<Savable> categories) {
		this.categoryList = categories;
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);
		//----------------------------------------------------------------------
		this.add(new Label("Title:"), 0, 0, 1, 1);
		titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);
		//----------------------------------------------------------------------
		this.add(new Label("Description:"), 0, 1, 1, 1);
		descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);
		//----------------------------------------------------------------------
		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		categoryField = new ComboBox<>();
		this.add(categoryField, 1, 2, 1, 1);
		for(Savable category : categoryList)
		{
			categoryTitles.add(((Category)category).getName());
		}
		categoryField.setItems(categoryTitles);
		//----------------------------------------------------------------------
		btnCancel = new Button("Cancel");
		this.add(btnCancel, 0, 3, 1, 1);
		//----------------------------------------------------------------------
		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		this.add(btnOK, 1, 3, 1, 1);
		//----------------------------------------------------------------------
		setSaveAction(new AddCategory());
		setCancelAction(new Cancel());
	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}

	public class AddCategory implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			try {
				String mainCategory = "";
				if(categoryField.getValue() == null)
				{
					mainCategory = titleField.getText();
				}
				else
				{
					mainCategory = categoryField.getValue().toString();
				}
				category = new Category(titleField.getText(), descriptionField.getText(),mainCategory);
				System.out.println(category.toString());
				categoryTitles.add(category.getName());
				categoryList.add(category);
				DBContext context = new DBContext();
				context.setStrategy(new QuestionTXT("CategoryFile.txt", categoryList));
				context.write();
				Stage stage = (Stage) btnOK.getScene().getWindow();
				stage.close();
				}
			catch(Exception ex) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("ERROR");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		}
	}

	public class Cancel implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();
		}
	}
}



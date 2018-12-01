package view.panels;

import controller.CategoryList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Category;

public class CategoryDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox categoryField;
	private CategoryList categories;
	private ObservableList<String> categorieTitles;
	private Category c;



	public CategoryDetailPane(CategoryList categoryList) {

		categories=categoryList;
		categorieTitles = FXCollections.observableArrayList(categories.getCategoryNames());

		this.setPrefHeight(150);
		this.setPrefWidth(300);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Title:"), 0, 0, 1, 1);
		titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);

		this.add(new Label("Description:"), 0, 1, 1, 1);
		descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);

		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		categoryField = new ComboBox<>();
		this.add(categoryField, 1, 2, 1, 1);
		categoryField.setItems(categorieTitles);

		btnCancel = new Button("Cancel");
		this.add(btnCancel, 0, 3, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		this.add(btnOK, 1, 3, 1, 1);
		setSaveAction(new AddCategory());
		btnOK.setOnAction(new AddCategory());
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
				c = new Category(titleField.getText(), descriptionField.getText());
				categories.addCategory(c);

				titleField.setText("");
				descriptionField.setText("");
				categorieTitles.add(c.getName());
				System.out.println(categories.getCategotyList().toString());
			}catch(Exception ex){
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
			Stage stage =(Stage) btnCancel.getScene().getWindow();
			stage.close();
		}
	}

}



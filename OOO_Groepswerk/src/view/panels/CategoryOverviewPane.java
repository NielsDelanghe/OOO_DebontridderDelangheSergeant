package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import controller.CategoryList;

public class CategoryOverviewPane extends GridPane{
	private TableView table;
	private Button btnNew;
	private CategoryList categories;
	
	public CategoryOverviewPane(CategoryList categories) {
		this.categories = categories;

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		this.add(new Label("Categories:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
		//----------------------------------------------------------------------
        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        table.getColumns().add(nameCol);
		//----------------------------------------------------------------------
        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().add(descriptionCol);
		//----------------------------------------------------------------------
		this.add(table, 0, 1, 2, 6);
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		setNewAction(new NewCategory());
		//----------------------------------------------------------------------
		table.setItems(categories.getCategotyList());
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) { btnNew.setOnAction(newAction); }
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	public CategoryList getCategoryList()
	{
		return categories;
	}

	private class NewCategory implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			CategoryDetailPane categoryDetailPane = new CategoryDetailPane(categories);
			Stage newCategoryStage = new Stage();
			Group root = new Group();
			Scene categoryScene = new Scene(root,250,150);
			root.getChildren().add(categoryDetailPane);
			newCategoryStage.setScene(categoryScene);
			newCategoryStage.show();
		}
	}
}

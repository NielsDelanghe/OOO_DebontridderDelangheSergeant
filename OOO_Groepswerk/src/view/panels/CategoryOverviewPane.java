package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Category;
import view.observers.CategoryList;

import java.util.Observable;
import java.util.Observer;


public class CategoryOverviewPane extends GridPane implements Observer {
	Observable observable;

	private TableView table;
	private Button btnNew;
	private model.Group gr = new model.Group();
	private CategoryList categories = new CategoryList();

	
	public CategoryOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Categories:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        table.getColumns().add(nameCol);


        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);

		table.setItems(gr.getList());
		
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		btnNew.setOnAction(new NewCategory());
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {

		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Category) {
			Category cat = (Category) o;
			categories.addCategory(cat);
			}

	}

	private class NewCategory implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event) {

			CategoryDetailPane categoryDetailPane = new CategoryDetailPane();
			Stage newCategoryStage = new Stage();

			Group root = new Group();
			Scene categoryScene = new Scene(root,250,150);

			root.getChildren().add(categoryDetailPane);
			newCategoryStage.setScene(categoryScene);
			newCategoryStage.show();
		}
	}

}

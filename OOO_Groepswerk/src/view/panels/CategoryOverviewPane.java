package view.panels;

import controller.DBContext;
import db.CategoryTXT;
import db.Savable;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import controller.CategoryList;
import model.Category;

public class CategoryOverviewPane extends GridPane{
	private TableView table;
	private Button btnNew;
	private CategoryList categories;
	private DBContext context;
	private ObservableList<Savable> savables;
	
	public CategoryOverviewPane(CategoryList categories,ObservableList<Savable> fileobjects) {
		this.categories = categories;
		savables = fileobjects;
		context = new DBContext();
		context.setStrategy(new CategoryTXT("CategoryFile.txt",savables));
		context.read();
		savables = context.getReadObjects();

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
		setEditAction(new EditCategory());
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		setNewAction(new NewCategory());
		//----------------------------------------------------------------------
		table.setItems(savables);
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) { btnNew.setOnAction(newAction); }
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	private class NewCategory implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			CategoryDetailPane categoryDetailPane = new CategoryDetailPane(categories,savables);
			Stage newCategoryStage = new Stage();
			Group root = new Group();
			Scene categoryScene = new Scene(root,250,150);
			root.getChildren().add(categoryDetailPane);
			newCategoryStage.setScene(categoryScene);
			newCategoryStage.show();
		}
	}

	private class EditCategory implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent mouseEvent) {
			if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				if(mouseEvent.getClickCount() == 2){
					TableView.TableViewSelectionModel<Category> tableView = table.getSelectionModel();
					int index = tableView.getSelectedIndex();
					Category category = tableView.getSelectedItem();
					CategoryUpdatePane categoryUpdatePane = new CategoryUpdatePane(categories,savables, category);
					Stage newCategoryStage = new Stage();
					Group root = new Group();
					Scene categoryScene = new Scene(root,250,150);
					root.getChildren().add(categoryUpdatePane);
					newCategoryStage.setScene(categoryScene);
					categoryUpdatePane.setTitleField(category.getName());
					categoryUpdatePane.setDescriptionField(category.getDescription());
					newCategoryStage.show();



				}
			}
		}
	}
}

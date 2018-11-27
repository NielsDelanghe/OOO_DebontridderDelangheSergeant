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
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;

	private ObservableList<Question> data = FXCollections.observableArrayList();

	private List<String> answers_q1 = new ArrayList<>();
	private List<String> answers_q2 = new ArrayList<>();

	private Category cat1 = new Category("Design principles", "The SOLID design principles", 4, "You should know the design of a principle");
	private Category cat2 = new Category("Design patterns", "A design pattern", 3, "Know all patterns");

	private Question q1;
	private Question q2;

	public QuestionOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Questions:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
		TableColumn nameCol = new TableColumn<>("Question");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
		table.getColumns().add(nameCol);
		TableColumn descriptionCol = new TableColumn<>("Category");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("category"));
		table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);

		answers_q1.add("Simple Factory");
		answers_q1.add("Singleton");
		answers_q1.add("Strategy");

		answers_q2.add("S");
		answers_q2.add("O");
		answers_q2.add("L");
		answers_q2.add("I");
		answers_q2.add("D");

		q1 = new Question("What pattern defines a family of algorithmes?", answers_q1, cat2.getName());
		q2 = new Question("What design principle has the least to do with Strategys?", answers_q2, cat1.getName());

		data.add(q1);
		data.add(q2);

		table.setItems(data);
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		btnNew.setOnAction(new NewQuestion());
	}

	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}

	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	private class NewQuestion implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			QuestionDetailPane questionDetailPane = new QuestionDetailPane();
			Stage newQuestionStage = new Stage();

			Group root = new Group();
			Scene questionScene = new Scene(root, 350, 300);

			root.getChildren().add(questionDetailPane);
			newQuestionStage.setScene(questionScene);
			newQuestionStage.show();


		}

	}
}

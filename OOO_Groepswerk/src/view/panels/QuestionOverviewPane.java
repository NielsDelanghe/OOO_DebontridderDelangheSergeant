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
import controller.QuestionList;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;

	private QuestionList questions;

	public QuestionOverviewPane(QuestionList questions) {
		this.questions = questions;

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);
		this.add(new Label("Questions:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
		//----------------------------------------------------------------------
		TableColumn nameCol = new TableColumn<>("Question");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
		table.getColumns().add(nameCol);
		//----------------------------------------------------------------------
		TableColumn descriptionCol = new TableColumn<>("Category");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("category"));
		table.getColumns().add(descriptionCol);
		//----------------------------------------------------------------------
		this.add(table, 0, 1, 2, 6);
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		setNewAction(new NewQuestion());
		//----------------------------------------------------------------------
		table.setItems(questions.getQuestions());
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

			QuestionDetailPane questionDetailPane = new QuestionDetailPane(questions);
			Stage newQuestionStage = new Stage();
			Group root = new Group();
			Scene questionScene = new Scene(root, 350, 300);
			root.getChildren().add(questionDetailPane);
			newQuestionStage.setScene(questionScene);
			newQuestionStage.show();
		}
	}
}
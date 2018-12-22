package view.panels;

import controller.DBContext;
import db.QuestionTXT;
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
import controller.QuestionList;
import model.Question;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private QuestionList questions;
	private DBContext context;
	private ObservableList<Savable> savables;

	public QuestionOverviewPane(QuestionList questions,ObservableList<Savable> fileobjects) {
		this.questions = questions;
		savables = fileobjects;
		context = new DBContext();
		context.setStrategy(new QuestionTXT("QuestionFile.txt",savables));
		context.read();
		savables = context.getReadObjects();

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
		table.setItems(savables);
		setEditAction(new EditCategory());
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

			QuestionDetailPane questionDetailPane = new QuestionDetailPane(questions,savables);
			Stage newQuestionStage = new Stage();
			Group root = new Group();
			Scene questionScene = new Scene(root, 350, 300);
			root.getChildren().add(questionDetailPane);
			newQuestionStage.setScene(questionScene);
			newQuestionStage.show();
		}
	}

	private class EditCategory implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent mouseEvent) {
			if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				if(mouseEvent.getClickCount() == 2){
					TableView.TableViewSelectionModel<Question> tableView = table.getSelectionModel();
					Question question = tableView.getSelectedItem();
					QuestionUpdatePane questionUpdatePane = new QuestionUpdatePane(questions,savables, question);
					Stage newQuestionStage = new Stage();
					Group root = new Group();
					Scene questionScene = new Scene(root,350,300);
					root.getChildren().add(questionUpdatePane);
					newQuestionStage.setScene(questionScene);
					questionUpdatePane.setQuestionField(question.getQuestion());
					questionUpdatePane.setStatementList(question.getPossible_answers());
					questionUpdatePane.setFeedbackField(question.getFeedback());
					newQuestionStage.show();
				}
			}
		}
	}
}
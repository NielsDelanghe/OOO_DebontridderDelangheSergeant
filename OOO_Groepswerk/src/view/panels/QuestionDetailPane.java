package view.panels;

import controller.QuestionList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Question;
import controller.CategoryList;


import java.util.ArrayList;
import java.util.List;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextArea statementsArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;
	private QuestionList questions = new QuestionList();
	private CategoryList categories = new CategoryList();
	private List<String> statementList = new ArrayList<>();
	private QuestionOverviewPane overviewPane = new QuestionOverviewPane();
	private Scene scene;

	public QuestionDetailPane() {
		this.setPrefHeight(300);
		this.setPrefWidth(320);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);

		add(new Label("Statement: "), 0, 1, 1, 1);
		statementField = new TextField();
		add(statementField, 1, 1, 2, 1);

		add(new Label("Statements: "), 0, 2, 1, 1);
		statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);
		statementsArea.setEditable(false);
		add(statementsArea, 1, 2, 2, 5);

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		btnAdd.setOnAction(new AddStatementListener());
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);

		add(new Label("Category: "), 0, 9, 1, 1);
		categoryField = new ComboBox();
		add(categoryField, 1, 9, 2, 1);

		add(new Label("Feedback: "), 0, 10, 1, 1);
		feedbackField = new TextField();
		add(feedbackField, 1, 10, 2, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 11, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		add(btnOK, 1, 11, 2, 1);


		categoryField.getItems().addAll(categories.getCategoryNames());

		btnAdd.setOnAction(new AddStatement());
		btnRemove.setOnAction(new RemoveStatement());
		btnOK.setOnAction(new AddQuestion());
		setCancelAction(new Close());

	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}

	class AddStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		}
	}

	class RemoveStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		}
	}

	class AddQuestion implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Question question = new Question(questionField.getText(),statementList,categoryField.getSelectionModel().getSelectedItem().toString(), feedbackField.getText(), 1);
			questions.addQuestion(question);
			overviewPane.addQuestion(question);




		}
	}

	class AddStatement implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			statementList.add(statementField.getText());
			String vorigeTekst = statementsArea.getText();
			for(String statement : statementList)
			{
				statementField.setText("");
				statementsArea.setText(vorigeTekst + statement + "\n");
			}
		}
	}

	class RemoveStatement implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			String teVerwijderen = statementField.getText();
			for(String statement : statementList) {
				if (statement.equals(teVerwijderen)) {
					statementList.remove(statement);
				}
				for(String s : statementList)
				{
					statementsArea.setText(s + "\n");
				}
			}
		}
	}

	class Close implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event) {
			Stage stage =(Stage) btnCancel.getScene().getWindow();
			stage.close();
		}
	}

}
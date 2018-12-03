package view.panels;

import java.util.ArrayList;
import java.util.List;

import controller.DBContext;
import controller.QuestionList;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Question;
import model.Test;

import javax.swing.*;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private ObservableList<Savable> questionList;
	private DBContext context;
	private Test test;
	private RadioButton answer;
	private ArrayList<RadioButton> answers = new ArrayList<>();
	private int possible_points = 0;
	private int earned_points = 0;

	public TestPane (){
		test = new Test();

		questionList = FXCollections.observableArrayList(new ArrayList<>());
		context = new DBContext();
		context.setStrategy(new QuestionTXT("QuestionFile.TXT", questionList));
		context.read();

		test.addAllQuestions(context.getReadObjects());

		this.setPrefHeight(300);
		this.setPrefWidth(750);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		questionField = new Label("Question: " + test.getFirstQuestion().getQuestion());
		add(questionField, 0, 0, 1, 1);

		statementGroup = new ToggleGroup();
		for(int i = 1; i < test.getFirstQuestion().getPossible_answers().size(); i++)
		{
			answer = new RadioButton(test.getFirstQuestion().getPossible_answers().get(i));
			answer.setToggleGroup(statementGroup);
			answers.add(answer);
			add(answer,0,i,1,1);
		}

		submitButton = new Button("Submit");
		add(submitButton,0,6,1,1);

		setProcessAnswerAction(new ProcessAnswerAction());
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

	class ProcessAnswerAction implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {

			possible_points += test.getFirstQuestion().getPoints();
			if(statementGroup.getSelectedToggle().toString() == test.getFirstQuestion().getPossible_answers().get(0))
			{
				earned_points += test.getFirstQuestion().getPoints();
			}

			for(RadioButton answer : answers)
			{
				answer.setText("");
			}

			questionField.setText("Question: " + test.ChangeQuestionAndGetNext().getQuestion());

			for(int i = 1; i < test.getFirstQuestion().getPossible_answers().size(); i++)
			{
				RadioButton answer = new RadioButton(test.getFirstQuestion().getPossible_answers().get(i));
				answer.setToggleGroup(statementGroup);
				add(answer,0,i,1,1);
			}
		}
	}
}


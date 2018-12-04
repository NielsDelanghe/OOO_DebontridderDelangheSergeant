package view.panels;

import java.util.ArrayList;
import java.util.List;
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
import model.Question;
import model.Test;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private ObservableList<Savable> questionList;
	private DBContext context;
	private Test test;
	private ArrayList<String> answers = new ArrayList<>();
	private ArrayList<RadioButton> radioButtons = new ArrayList<>();
	private ArrayList<Question> questions = new ArrayList<>();

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
		//----------------------------------------------------------------------
		questionField = new Label("Question: " + test.getFirstQuestion().getQuestion());
		add(questionField, 0, 0, 1, 1);
		//----------------------------------------------------------------------
		statementGroup = new ToggleGroup();
		answers.addAll(test.getFirstQuestion().getPossible_answers());
		for(int i = 0; i < answers.size(); i++)
		{
			System.out.println(i + " " + answers.get(i));
			RadioButton button = new RadioButton(answers.get(i));
			radioButtons.add(button);
			button.setToggleGroup(statementGroup);
			add(button,0,i+1);
		}
		//----------------------------------------------------------------------
		submitButton = new Button("Submit");
		add(submitButton,0,10,1,1);
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
			if(!test.getMap().containsKey(test.getFirstQuestion().getCategory()))
			{
				test.addKey(test.getFirstQuestion().getCategory());
			}

			test.addPointsToCategory(test.getFirstQuestion().getCategory());
			getChildren().clear();
			answers.clear();
			statementGroup = new ToggleGroup();
			if(test.hasNext()) {
				//change question
				questionField.setText("Question: " + test.ChangeQuestionAndGetNext().getQuestion());
				add(questionField,0,0);

				//update radiobuttons for new question
				answers.addAll(test.getFirstQuestion().getPossible_answers());
				for(int i = 0; i < answers.size(); i++)
				{
					RadioButton button = new RadioButton(answers.get(i));
					radioButtons.add(button);
					button.setToggleGroup(statementGroup);
					add(button,0,i+1);
					setProcessAnswerAction(new ProcessAnswerAction());
				}
				add(submitButton,0,10,1,1);
			}
			else
			{
				questionField.setText("Klaar" + test.getMap());
				add(questionField,0,0);
			}
		}
	}
}


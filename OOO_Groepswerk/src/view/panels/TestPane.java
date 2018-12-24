package view.panels;

import java.util.ArrayList;
import java.util.Collections;
import controller.DBContext;
import controller.TestController;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private ObservableList<Savable> questionList;
	private ObservableList<Savable> scoreList;
	private ObservableList<Savable> randomList;
	private DBContext context;
	private ArrayList<String> answers = new ArrayList<>();
	private ArrayList<RadioButton> radioButtons = new ArrayList<>();
	private TestController testController;

	public TestPane (ObservableList<Savable> scores){
		questionList = FXCollections.observableArrayList(new ArrayList<>());
		scoreList = scores;
		context = new DBContext();
		context.setStrategy(new QuestionTXT(questionList));
		context.read();

		randomList = context.getReadObjects();
		Collections.shuffle(randomList);
		testController = new TestController(randomList);
		testController.prepareQuestions();
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);
		//----------------------------------------------------------------------
		questionField = new Label("Question: " + testController.getFirstQuestion());
		add(questionField, 0, 0, 1, 1);
		//----------------------------------------------------------------------
		statementGroup = new ToggleGroup();
		answers.addAll(testController.getPossibleAnswers());
		for(int i = 0; i < answers.size(); i++)
		{
			RadioButton button = new RadioButton(answers.get(i));
			button.setUserData(answers.get(i));
			radioButtons.add(button);
			button.setToggleGroup(statementGroup);
			add(button,0,i+1);
		}
		//----------------------------------------------------------------------
		submitButton = new Button("Submit");
		add(submitButton,0,10,1,1);
		setProcessAnswerAction(new ProcessAnswerAction());
	}

	private void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {submitButton.setOnAction(processAnswerAction);}
	private void setCloseAction(EventHandler<ActionEvent> closeAction) { submitButton.setOnAction(closeAction); }

	class Close implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) submitButton.getScene().getWindow();
			stage.close();
		}
	}

	class ProcessAnswerAction implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			//If category doesn't exist in test, add category to test
			testController.addCategoryToTestIfCategoryDoesntExist();
			//Set the first answer as the correct answer
			testController.isCorrect(statementGroup.getSelectedToggle().getUserData().toString());
			testController.addQuestion();
			//Clear everything on the screen
			getChildren().clear();
			answers.clear();
			statementGroup = new ToggleGroup();
			//----------------------------------
			if(testController.testHasNext())
			{
				loadNextQuestion();
			}
			else
			{
				showCorrectEvaluation();
			}
		}
		private void showFeedback()
		{
			questionField.setText(testController.getFeedback());
			testController.saveScore(scoreList);
		}
		private void showScore()
		{
			questionField.setText("Your score is: " + testController.showScoreResult());
			testController.saveScore(scoreList);
		}

		private void loadNextQuestion()
		{
			//change question
			questionField.setText("Question: " + testController.getNextQuestion());
			add(questionField,0,0);
			//update radiobuttons for new question
			answers.addAll(testController.getPossibleAnswers());
			for(int i = 0; i < answers.size(); i++)
			{
				RadioButton button = new RadioButton(answers.get(i));
				button.setUserData(answers.get(i));
				radioButtons.add(button);
				button.setToggleGroup(statementGroup);
				add(button,0,i+1);
				setProcessAnswerAction(new ProcessAnswerAction());
			}
			add(submitButton,0,10,1,1);
		}

		private void showCorrectEvaluation()
		{
			switch (testController.getProperty())
			{
				case "score": showScore(); break;
				case "feedback": showFeedback(); break;
				default: showScore(); break;
			}
			add(questionField,0,0);
			submitButton = new Button("Close");
			add(submitButton,0,10,1,1);
			setCloseAction(new Close());
		}
	}
}


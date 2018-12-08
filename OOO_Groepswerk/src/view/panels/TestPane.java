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
import javafx.stage.Stage;
import model.Question;
import model.Test;

import javax.swing.text.html.HTML;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private ObservableList<Savable> questionList;
	private DBContext context;
	private Test test;
	private ArrayList<String> answers = new ArrayList<>();
	private ArrayList<RadioButton> radioButtons = new ArrayList<>();

	public TestPane (){
		test = new Test();
		questionList = FXCollections.observableArrayList(new ArrayList<>());
		context = new DBContext();
		context.setStrategy(new QuestionTXT("QuestionFile.TXT", questionList));
		context.read();
		test.addAllQuestionsToQueue(context.getReadObjects());

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

	public void setFeedbackAction(EventHandler<ActionEvent> feedbackAction)
	{
		submitButton.setOnAction(feedbackAction);
	}

	class FeedbackAction implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event) {

			getChildren().clear();

			String feedback="";

			for(Question question : test.getQuestions())
			{
				if(question.getCorrect()==false)
				{
					feedback+=question.getQuestion() + "\n\t" + question.getFeedback()+"\n\n";
				}
			}
			
			if(feedback.equals(""))
			{
				feedback="Proficiat u hebt alles juist";
			}

			questionField.setText(feedback);
			add(questionField,0,0);
			submitButton = new Button("Close");
			add(submitButton,0,10,1,1);
			setCloseAction(new Close());


		}
	}

	public void setCloseAction(EventHandler<ActionEvent> closeAction) {
		submitButton.setOnAction(closeAction);
	}

	public class Close implements EventHandler<ActionEvent>
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
		public void handle(ActionEvent event) {

			if(!test.getMap().containsKey(test.getFirstQuestion().getCategory()))
			{
				test.addKey(test.getFirstQuestion().getCategory());
			}

			if(statementGroup.getSelectedToggle().getUserData().equals(test.getFirstQuestion().getPossible_answers().get(0)))
			{
				test.getFirstQuestion().setCorrect(true);
				test.addPointsToCategory(test.getFirstQuestion().getCategory());
			}

			test.addQuestionToList(test.getFirstQuestion());

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
					button.setUserData(answers.get(i));
					radioButtons.add(button);
					button.setToggleGroup(statementGroup);
					add(button,0,i+1);
					setProcessAnswerAction(new ProcessAnswerAction());
				}
				add(submitButton,0,10,1,1);
			}
			else
			{
				int score =0;
				for(Question question : test.getQuestions())
				{
					if(question.getCorrect()==true)
					{
						score++;
					}
				}

				questionField.setText("Youre score is: " + score +"/"+ test.getQuestions().size() + "\n" + test.getCategorieAndPoints());
				add(questionField,0,0);
				submitButton = new Button("Get feedback");
				add(submitButton,0,10,1,1);
				setFeedbackAction(new FeedbackAction());

			}
		}
	}
}


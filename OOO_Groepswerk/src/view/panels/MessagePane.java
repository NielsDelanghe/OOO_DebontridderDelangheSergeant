package view.panels;

import controller.DBContext;
import db.EvaluationTXT;
import db.Savable;
import evaluationStates.Completed;
import evaluationStates.EvaluationState;
import evaluationStates.NeverCompleted;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Test;

public class MessagePane extends GridPane {
	private Button testButton;
	private ObservableList<Savable> scoreList;
	private DBContext context;
	private Label text;
	private Test evaluation;
	private EvaluationState state;

	public MessagePane (){
		this.evaluation = new Test();
		state = new NeverCompleted(evaluation);
		context = new DBContext();
		context.setStrategy(new EvaluationTXT("resources/db/Evaluation.txt",scoreList));
		context.read();
		scoreList = context.getReadObjects();
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);
		testButton = new Button("Evaluate");
		setStartTestAction(new StartTest());
		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);
		this.text = new Label();
		if(scoreList.size() == 0)
		{
			text.setText(state.toString());
		}

		else
		{
			state = new Completed(evaluation);
			evaluation = (Test) context.getReadObjects().get(context.getReadObjects().size()-1);
			text.setText(state.toString() +", your score was: "+ evaluation.getScore());
		}
		add(text,0,0);
	}

	public void setStartTestAction(EventHandler<ActionEvent> startTestAction) { testButton.setOnAction(startTestAction); }

	class StartTest implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			TestPane testPane = new TestPane(scoreList);
			Stage newTestStage = new Stage();
			Group root = new Group();
			Scene testScene = new Scene(root, 750, 300);
			root.getChildren().add(testPane);
			newTestStage.setScene(testScene);
			newTestStage.show();
		}
	}

}
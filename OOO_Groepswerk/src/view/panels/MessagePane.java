package view.panels;

import controller.DBContext;
import db.CategoryTXT;
import db.EvaluationTXT;
import db.Savable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private ObservableList<Savable> savables;
	private DBContext context;

	public MessagePane (ObservableList<Savable> fileobjects){
		setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		testButton = new Button("Evaluate");
		setStartTestAction(new StartTest());
		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);

		savables=fileobjects;
		Test test = new Test();
		test.setScore(2);
		test.setMaxPossibleScore(6);
		Test test2 = new Test();
		test2.setScore(8);
		test2.setMaxPossibleScore(8);
		savables.add(test);
		savables.add(test2);
		context = new DBContext();
		context.setStrategy(new EvaluationTXT("Evaluation.txt",savables));
		context.write();
		context.read();
		savables = context.getReadObjects();
		System.out.println(savables);
	}

	public void setStartTestAction(EventHandler<ActionEvent> startTestAction) { testButton.setOnAction(startTestAction); }

	class StartTest implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			TestPane testPane = new TestPane();
			Stage newTestStage = new Stage();
			Group root = new Group();
			Scene testScene = new Scene(root, 750, 300);
			root.getChildren().add(testPane);
			newTestStage.setScene(testScene);
			newTestStage.show();
		}
	}

}
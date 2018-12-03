package view.panels;

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

public class MessagePane extends GridPane {
	private Button testButton;

	public MessagePane (){
		setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		testButton = new Button("Evaluate");
		setStartTestAction(new StartTest());
		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);
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
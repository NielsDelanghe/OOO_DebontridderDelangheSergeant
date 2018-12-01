package application;

import controller.CategoryList;
import controller.QuestionList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {

	private CategoryList categoryList;
	private QuestionList questionList;

	@Override
	public void start(Stage primaryStage) {

		try {

			categoryList=new CategoryList();
			questionList = new QuestionList();

			QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(questionList);
			QuestionDetailPane questionDetailPane = new QuestionDetailPane(questionList);

			CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane(categoryList);
			CategoryDetailPane categoryDetailPanel = new CategoryDetailPane(categoryList);

			TestPane testPane = new TestPane();
			MessagePane messagePane = new MessagePane();

			Group root = new Group();
			Scene scene = new Scene(root, 750, 400);

			BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane);
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(borderPane);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

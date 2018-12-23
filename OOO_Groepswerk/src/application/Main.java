/*
package application;

import controller.CategoryList;
import controller.QuestionList;
import db.Savable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.*;

import java.util.ArrayList;

public class Main extends Application {

	private CategoryList categoryList;
	private QuestionList questionList;
	private ObservableList<Savable> categories;
	private ObservableList<Savable> questions;
	private ObservableList<Savable> scores;

	@Override
	public void start(Stage primaryStage) {
		try {
			categoryList = new CategoryList();
			questionList = new QuestionList();
			categories = FXCollections.observableArrayList(new ArrayList<>());
			questions = FXCollections.observableArrayList(new ArrayList<>());
			scores = FXCollections.observableArrayList(new ArrayList<>());

			QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(questionList,questions);
			QuestionDetailPane questionDetailPane = new QuestionDetailPane(questionList,questions);

			CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane(categoryList,categories);
			CategoryDetailPane categoryDetailPanel = new CategoryDetailPane(categoryList,categories);

			PropertyPane propertyPanel = new PropertyPane();

			TestPane testPane = new TestPane(scores);
			MessagePane messagePane = new MessagePane(scores);

			Group root = new Group();
			Scene scene = new Scene(root, 750, 400);

			BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane, propertyPanel);
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
*/

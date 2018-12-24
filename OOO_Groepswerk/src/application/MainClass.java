package application;

import controller.CategoryAndQuestionController;
import controller.DBContext;
import db.CategoryTXT;
import db.QuestionTXT;
import db.Savable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends Application{

    private DBContext questionDbContext;
    private DBContext categoryDbContext;
    private ObservableList<Savable> questions = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Savable> categories = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Savable> scores;

    @Override
    public void start(Stage primaryStage) {
    try {
        this.questionDbContext = new DBContext();
        this.categoryDbContext = new DBContext();
        this.questionDbContext.setStrategy(new QuestionTXT( questions));
        this.categoryDbContext.setStrategy(new CategoryTXT(categories));
        questionDbContext.read();
        categoryDbContext.read();
        questions = questionDbContext.getReadObjects();
        categories = categoryDbContext.getReadObjects();

        QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(questions, categories);
        CategoryOverviewPane categoryOverviewPane = new CategoryOverviewPane(categories);
        CategoryAndQuestionController controller = new CategoryAndQuestionController(questionOverviewPane, categoryOverviewPane);


        scores = FXCollections.observableArrayList(new ArrayList<>());

        PropertyPane propertyPanel = null;
        try {
            propertyPanel = new PropertyPane();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        TestPane testPane = new TestPane(scores);
        MessagePane messagePane = new MessagePane();

        Group root = new Group();
        Scene scene = new Scene(root, 750, 400);

        BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPane, questionOverviewPane, propertyPanel);
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

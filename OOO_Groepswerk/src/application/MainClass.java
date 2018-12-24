package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{

    private Launcher launcher;

    @Override
    public void start(Stage primaryStage) {
        launcher = new Launcher();
        launcher.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

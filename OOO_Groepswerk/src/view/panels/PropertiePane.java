package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PropertiePane extends GridPane {
    private TableView table;
    private Button submitButton;
    private ComboBox propertieField;
    private ArrayList<String> lijst;
    private Properties properties;
    private ToggleGroup statementGroup;
    private RadioButton answer;
    private Label questionField;


    public PropertiePane() throws IOException {


        lijst= new ArrayList<String>();
        lijst.add("score");
        lijst.add("geen score");
        questionField= new Label();
        questionField.setText("");
        add(questionField,0,10);



        statementGroup = new ToggleGroup();
        for(int i = 0; i < lijst.size(); i++)
        {
            answer = new RadioButton(lijst.get(i));
            answer.setUserData(lijst.get(i));
            answer.setToggleGroup(statementGroup);
            add(answer,0,i,1,1);
        }

        submitButton = new Button("Submit");
        add(submitButton,0,6,1,1);
        setSaveAction(new saveEvaluation());



    }

    public void setSaveAction(EventHandler<ActionEvent> saveAction) {
        submitButton.setOnAction(saveAction);
    }

    private class saveEvaluation implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String woord = null;
            try {
                Object x = statementGroup.getSelectedToggle().getUserData();
                woord= (String) x;
                questionField.setText("De evaluatie is veranderd naar " +woord);
            } catch (NullPointerException e){
                woord = "geen";
            }

            Properties properties = new Properties();
            InputStream is;
            try {
                is = new FileInputStream("Setup.properties");
                properties.load(is);
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            if(woord != null && woord != "geen"){
                properties.setProperty("evaluation.mode", woord);

            }




        }
    }
}

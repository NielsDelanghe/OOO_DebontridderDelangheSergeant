package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import jdk.nashorn.internal.objects.annotations.Property;


import java.io.*;

import java.util.ArrayList;
import java.util.Properties;

public class PropertyPane extends GridPane {
    private TableView table;
    private Button submitButton;
    private ComboBox propertyField;
    private ArrayList<String> lijst;
    private Properties properties;
    private ToggleGroup statementGroup;
    private RadioButton answer;
    private Label questionField;

    public PropertyPane() throws IOException {
        lijst = new ArrayList<String>();
        lijst.add("Score");
        lijst.add("Feedback");
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
            String selection = null;
            Object selectedRadioButton = statementGroup.getSelectedToggle().getUserData();
            selection = (String) selectedRadioButton;
            questionField.setText("Evaluation method changed to: " + selection);
            write(selection);
            }
        }

        private void write(String selection)
        {
            try
            {
                properties = new Properties();
                properties.setProperty("evaluation.mode", selection);
                File file = new File("evaluation.properties");
                OutputStream out = new FileOutputStream(file);
                properties.store(out,"Properties");
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

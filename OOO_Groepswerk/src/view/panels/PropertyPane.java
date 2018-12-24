package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyPane extends GridPane {
    private Button submitButton;
    private ArrayList<String> list;
    private Properties properties;
    private ToggleGroup statementGroup;
    private RadioButton answer;
    private Label questionField;
    private InputStream inputStream;

    public PropertyPane() throws IOException {
        list = new ArrayList<>();
        list.add("Score");
        list.add("Feedback");
        list.add("TXT");
        list.add("Excel");
        questionField= new Label();
        questionField.setText("");
        add(questionField,0,10);

        statementGroup = new ToggleGroup();
        for(int i = 0; i < list.size(); i++)
        {
            answer = new RadioButton(list.get(i));
            answer.setUserData(list.get(i));
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
            String selection;
            Object selectedRadioButton = statementGroup.getSelectedToggle().getUserData();
            selection = (String) selectedRadioButton;
            if(selection.equals("Score") || selection.equals("Feedback"))
            {
                questionField.setText("Evaluation method changed to: " + selection);
            }
            else
            {
                questionField.setText("You will now save to: " + selection);
            }
            write(selection.toLowerCase());
            }
        }

        private void write(String selection)
        {
            try
            {
                File file;
                properties = new Properties();
                if(selection.equals("score") || selection.equals("feedback"))
                {
                    properties.setProperty("evaluation.mode", selection);
                    file = new File("resources/db/evaluation.properties");
                }
                else
                {

                    properties.setProperty("save.mode", selection);
                    file = new File("resources/db/save.properties");

                }
                OutputStream out = new FileOutputStream(file);
                properties.store(out,"Properties");
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

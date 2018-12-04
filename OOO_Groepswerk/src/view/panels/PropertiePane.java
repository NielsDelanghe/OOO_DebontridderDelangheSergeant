package view.panels;
import javafx.collections.ObservableList;
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

    public PropertiePane() throws IOException {

        lijst= new ArrayList<String>();
        lijst.add("score");
        lijst.add("geen score");



        statementGroup = new ToggleGroup();
        for(int i = 0; i < lijst.size(); i++)
        {
            answer = new RadioButton(lijst.get(i));
            answer.setToggleGroup(statementGroup);
            add(answer,0,i,1,1);
        }

        submitButton = new Button("Submit");
        add(submitButton,0,6,1,1);



    }
}

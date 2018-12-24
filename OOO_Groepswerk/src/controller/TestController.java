package controller;

import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Question;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class TestController {

    private ObservableList<Savable>questionList;

    public TestController(ObservableList<Savable> questions)
    {
        questionList = FXCollections.observableArrayList(new ArrayList<>());
        this.questionList = questions;
    }

    public String getProperty()
    {
        Properties properties = new Properties();
        InputStream is;
        try {
            File file = new File("resources/db/evaluation.properties");
            is = this.getClass().getClassLoader().getResourceAsStream("resource/db/evaluation.properties");
        }
        catch ( Exception e ) { is = null; }
        try {
            if ( is == null ) {
                is = getClass().getClassLoader().getResourceAsStream("resources/db/evaluation.properties");
            }
            properties.load( is );
        }
        catch ( Exception e ) { }
        //--------------------------------------------------
        String evaluationMode = properties.getProperty("evaluation.mode", "score");
        return evaluationMode;
    }

    public String getFeedback()
    {
        String feedback = "";
        for(Savable question : questionList)
        {

            if(((Question)question).getCorrect() == false)
            {
                feedback +=((Question)question).getQuestion() + "\n\t" + ((Question)question).getFeedback()+"\n\n";
            }

        }
        if(feedback.equals("") || getScore() == questionList.size())
        {
            feedback = "Congratulations, all answers are correct!";
        }
        return feedback;
    }

    public int getScore()
    {
        int score =0;
        for(Savable question : questionList)
        {

            if(((Question)question).getCorrect() ==true)
            {
               score++;
            }

        }
        return score;
    }

    public int getMaxScore()
    {
        return questionList.size();
    }


}

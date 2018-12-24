package controller;

import db.EvaluationTXT;
import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Question;
import model.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        File file;
        try {
            file = new File("resources/db/evaluation.properties");
            is = this.getClass().getClassLoader().getResourceAsStream("resources/db/evaluation.properties");
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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

    public void writeScore(ObservableList<Savable> scoreList)
    {
        DBContext context;
        context = new DBContext();
        context.setStrategy(new EvaluationTXT(scoreList));
        context.write();
    }


}

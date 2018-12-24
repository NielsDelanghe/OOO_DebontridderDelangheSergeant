package controller;

import db.EvaluationTXT;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.ObservableList;
import model.Question;
import model.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class TestController {

    private ObservableList<Savable>questionList;
    private Test test;
    private DBContext context;

    public TestController(ObservableList<Savable> questions)
    {
        this.test = new Test();
        this.questionList = questions;
        context = new DBContext();
        context.setStrategy(new QuestionTXT(questionList));
        context.read();
    }

    public void prepareQuestions()
    {
        test.addAllQuestionsToQueue(questionList);
    }

    public String getFirstQuestion()
    {
        return test.getFirstQuestion().getQuestion();
    }

    public void addCategoryToTestIfCategoryDoesntExist()
    {
        if(!test.getMap().containsKey(test.getFirstQuestion().getCategory()))
        {
            test.addKey(test.getFirstQuestion().getCategory());
        }
    }

    public void isCorrect(String answer)
    {
        if(answer.equals( test.getFirstQuestion().getPossible_answers().get(0)))
        {
            test.getFirstQuestion().setCorrect(true);
            test.addPointsToCategory(test.getFirstQuestion().getCategory());
        }
    }

    public void addQuestion()
    {
        test.addQuestionToList(test.getFirstQuestion());
    }

    public List<String> getPossibleAnswers()
    {
        return test.getFirstQuestion().getPossible_answers();
    }

    public boolean testHasNext()
    {
        return test.hasNext();
    }

    public void saveScore(ObservableList<Savable> scoreList)
    {
        test.setScore(this.getScore());
        test.setMaxPossibleScore(this.getMaxScore());
        scoreList.add(test);
        this.writeScore(scoreList);
    }

    public String getNextQuestion()
    {
        return test.ChangeQuestionAndGetNext().getQuestion();
    }

    public String showScoreResult()
    {
        return this.getScore() +"/"+ test.getQuestions().size() + "\n" + test.getCategorieAndPoints();
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
        if(feedback.equals("") || getScore() == test.getQuestions().size())
        {
            feedback = "Congratulations, all answers are correct!";
        }
        return feedback;
    }

    public int getScore()
    {
        int score =0;
        for(Question question : test.getQuestions())
        {
            if(question.getCorrect() == true)
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

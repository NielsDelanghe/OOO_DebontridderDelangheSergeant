package model;

import controller.DBContext;
import db.Savable;
import evaluationStates.Completed;
import evaluationStates.EvaluationState;
import evaluationStates.NeverCompleted;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.util.*;

public class Test implements Savable{

    private HashMap<String,Integer> map;
    private Queue<Savable> queue;
    private List<Question> questions;

    private int score;
    private int maxPossibleScore;

    private EvaluationState state;
    private NeverCompleted neverCompletedState;
    private Completed completedState;


    public Test()
    {
        map = new HashMap<>();
        queue = new LinkedList<>();
        questions = new ArrayList<>();
        neverCompletedState = new NeverCompleted(this);
        completedState = new Completed(this);
        score=0;
        maxPossibleScore=0;
    }

    public void setScore(int score)
    {
        this.score=score;
    }

    public void setMaxPossibleScore(int max)
    {
        this.maxPossibleScore=max;
    }

    public void addKey(String category)
    {
        map.put(category,0);
    }

    public void addPointsToCategory(String category)
    {
        map.put(category,map.get(category)+1);
    }

    public HashMap<String,Integer> getMap()
    {
        return map;
    }

    public String getCategorieAndPoints()
    {
        String out="";
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            int v = entry.getValue();
            out+=k+": "+ v + "/" +this.getLengthOfCategory(k) + "\n";
        }
        return out;
    }

    public void addQuestionToList(Question question)
    {
        questions.add(question);
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    private int getLengthOfCategory(String categorie)
    {
        int amount =0;

        for(Question question : questions)
        {
            if(question.getCategory().equals(categorie))
            {
                amount++;
            }
        }
        return amount;
    }

    public void addAllQuestionsToQueue(ObservableList<Savable> questions)
    {
        queue.addAll(questions);
    }

    public Question getFirstQuestion()
    {
        Savable savable = queue.peek();

        Question question = (Question) savable;

        return question;
    }

    public Question ChangeQuestionAndGetNext()
    {
        queue.poll();
        Question question = getFirstQuestion();
        return question;
    }

    public boolean hasNext()
    {
        return queue.size() > 1;
    }

    public void changeState(EvaluationState evaluationState)
    {
        this.state=evaluationState;
    }

    public void neverCompleted(){ state.neverCompleted();}

    public void completed(){state.completed();}

    public NeverCompleted getNeverCompletedState(){return neverCompletedState;}

    public Completed getCompletedState(){return completedState;}

    public String getScore(){return score +"/"+ maxPossibleScore;}

    public String toString()
    {
        return score + "\t" + maxPossibleScore + "\n";
    }
}

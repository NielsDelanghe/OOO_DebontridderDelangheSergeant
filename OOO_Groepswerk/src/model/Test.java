package model;

import db.Savable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.util.*;

public class Test {

    private HashMap<String,Integer> map;
    private Queue<Savable> queue;

    public Test()
    {
        map = new HashMap<>();
        queue = new LinkedList<>();
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

    public void addAllQuestions(ObservableList<Savable> questions)
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

    public static void main(String args[])
    {
        List<String> answers_q1 = new ArrayList<>();
        List<String> answers_q2 = new ArrayList<>();

        Category cat1 = new Category("Design principles", "The SOLID design principles");
        Category cat2 = new Category("Design patterns", "A design pattern");

        Question q1;
        Question q2;

        answers_q1.add("Simple Factory");
        answers_q1.add("Singleton");
        answers_q1.add("Strategy");

        answers_q2.add("S");
        answers_q2.add("O");
        answers_q2.add("L");
        answers_q2.add("I");
        answers_q2.add("D");

        q1 = new Question("What pattern defines a family of algorithmes?", cat2.getName(), "feedback", 5,answers_q1);
        q2 = new Question("What design principle has the least to do with Strategys?", cat1.getName(), "feedback", 3,answers_q2);

        System.out.println(q1.toString());

        Test test = new Test();
        ObservableList<Savable> questions = FXCollections.observableArrayList(new ArrayList<>());
        questions.addAll(q1,q2);
        test.addAllQuestions(questions);

        int answer=3;

        while(answer != 1)
        {
            answer = Integer.parseInt(JOptionPane.showInputDialog(null,test.getFirstQuestion().getQuestion()));
        }

        if(answer==1);
        {
            JOptionPane.showMessageDialog(null,test.ChangeQuestionAndGetNext().getQuestion());
        }

    }

}

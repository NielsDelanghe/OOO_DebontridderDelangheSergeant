package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    private ObservableList<Question> questions;

    public QuestionList()
    {
        questions = FXCollections.observableArrayList(new ArrayList<>());
        List<String> answers_q1 = new ArrayList<>();
        List<String> answers_q2 = new ArrayList<>();

        Category cat1 = new Category("Design principles", "The SOLID design principles","Design principles");
        Category cat2 = new Category("Design patterns", "A design pattern","Design patterns");

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

        q1 = new Question("What pattern defines a family of algorithmes?", cat2.getName(), "feedback", 5,false,answers_q1);
        q2 = new Question("What design principle has the least to do with Strategys?", cat1.getName(), "feedback", 3,false,answers_q2);

        questions.add(q1);
        questions.add(q2);
    }

    public void addQuestion(Question question)
    {
        if(questions.contains(question))
        {
            throw new IllegalArgumentException("Question already exists");
        }
        else if(question == null)
        {
            throw new IllegalArgumentException("Question can't be empty");
        }
        questions.add(question);
    }

    public ObservableList<Question> getQuestions()
    {
        return questions;
    }


    public void removeQuestion(Question previus) {
        if(questions.contains(previus))
        {
            throw new IllegalArgumentException("Given question doesn't exists");
        }

        else if(previus == null)
        {
            throw new IllegalArgumentException("question can't be empty");
        }
        questions.remove(previus);
    }
}
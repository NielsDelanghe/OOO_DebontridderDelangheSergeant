package controller;

import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    private List<Question> questions;

    public QuestionList()
    {
        questions = new ArrayList<>();
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

        q1 = new Question("What pattern defines a family of algorithmes?", answers_q1, cat2.getName(), "feedback", 5);
        q2 = new Question("What design principle has the least to do with Strategys?", answers_q2, cat1.getName(), "feedback", 3);

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

    public List<Question> getQuestions()
    {
        return questions;
    }


}
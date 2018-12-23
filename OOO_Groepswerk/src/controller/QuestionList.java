package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Question;
import java.util.ArrayList;

public class QuestionList {

    private ObservableList<Question> questions;

    public QuestionList()
    {
        questions = FXCollections.observableArrayList(new ArrayList<>());
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
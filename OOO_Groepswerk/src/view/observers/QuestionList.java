package view.observers;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    private List<Question> questions;

    public QuestionList(){ questions = new ArrayList<>(); }

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

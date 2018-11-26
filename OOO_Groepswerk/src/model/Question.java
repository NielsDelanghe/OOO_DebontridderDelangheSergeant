package model;

import java.util.List;

public class Question {

    private String question;
    private List<String> possible_answers;
    private Category category;

    public Question(String question, List<String> possible_answers, Category category)
    {
        setQuestion(question);
        setPossible_answers(possible_answers);
        setCategory(category);
    }

    public void setQuestion(String question) {
        if(question == null || question.trim().length() == 0)
        {
            throw new IllegalArgumentException("The text of a question can't be empty");
        }
        this.question = question;

        //
    }

    public void setPossible_answers(List possible_answers) {
        if(possible_answers.size() < 1)
        {
            throw new IllegalArgumentException("Possible answers of a question must contain at least 1 answer");
        }
        this.possible_answers = possible_answers;
    }

    public void setCategory(Category category) {
        if(category == null)
        {
            throw new IllegalArgumentException("The category of a question can't be empty");
        }
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getPossible_answers() {
        return possible_answers;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString()
    {
        String result = "";
        result += "Question: " + this.getQuestion();
        result += "\nPossible answers: ";
                for(String answer : possible_answers)
                {
                    result += "\n" + answer;
                }
        result += "Category: " + this.getCategory();
        result += "Feedback: " + this.getCategory().getFeedback();
        result += "Points: " + this.getCategory().getPoints();
        return result;
    }
}

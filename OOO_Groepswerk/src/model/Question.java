package model;

import db.Savable;

import java.util.List;

public class Question implements Savable {

    private String question;
    private List<String> possible_answers;
    private String category;
    private String feedback;
    private int points;
    private boolean correct;

    public Question(String question, String category, String feedback, int points,boolean correct,List<String> possible_answers)
    {
        this.setQuestion(question);
        this.setPossible_answers(possible_answers);
        this.setCategory(category);
        this.setFeedback(feedback);
        this.setPoints(points);
        this.setCorrect(correct);
    }

    public Question()
    {}

    public void setQuestion(String question) {
        if(question == null || question.trim().length() == 0)
        {
            throw new IllegalArgumentException("The text of a question can't be empty");
        }
        this.question = question;
    }

    public void setPossible_answers(List<String> possible_answers) {
        if(possible_answers.size() < 1)
        {
            throw new IllegalArgumentException("Possible answers of a question must contain at least 1 answer");
        }
        this.possible_answers = possible_answers;
    }

    public void setCategory(String category) {
        if(category == null || category.trim().length() == 0)
        {
            throw new IllegalArgumentException("The category of a question can't be empty");
        }
        this.category = category;
    }

    public void setFeedback(String feedback) {
        if(feedback == null || feedback.trim().length() == 0)
        {
            throw new IllegalArgumentException("The feedback of a question can't be empty");
        }
        this.feedback = feedback;
    }

    public void setPoints(int points) {
        if(points < 1)
        {
            throw new IllegalArgumentException("The points of a question should be at least 1");
        }
        this.points = points;
    }

    public void setCorrect(boolean correct)
    {
        this.correct = correct;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getPossible_answers() {
        return possible_answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getFeedback() { return feedback; }

    public int getPoints() {
        return points;
    }

    public boolean getCorrect()
    {
        return this.correct;
    }

    @Override
    public String toString()
    {
        String result = question + "\t" + category +"\t" + feedback +"\t" + points +"\t" + correct;
        for(String answer : possible_answers)
        {
            result += "\t" + answer;
        }
        result +="\n";
        return result;
    }
}
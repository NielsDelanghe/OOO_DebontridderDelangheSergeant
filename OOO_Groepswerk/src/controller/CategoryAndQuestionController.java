package controller;

import db.CategoryTXT;
import db.QuestionTXT;
import db.Savable;
import javafx.collections.ObservableList;
import model.Question;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;

public class CategoryAndQuestionController {

    private ObservableList<Savable> questions;
    private ObservableList<Savable> categories;
    private DBContext questionDbContext;
    private DBContext categoryDbContext;

    public CategoryAndQuestionController(QuestionDetailPane questionDetailPane, CategoryDetailPane categoryDetailPane)
    {
        this.questionDbContext.setStrategy(new QuestionTXT("QuestionFile.txt", questions));
        this.categoryDbContext.setStrategy(new CategoryTXT("CategoryFile.txt", categories));
        questionDbContext.read();
        categoryDbContext.read();
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
}

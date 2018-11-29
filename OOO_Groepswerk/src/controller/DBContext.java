package controller;

import db.CategoryTXT;
import db.DBStrategy;
import db.QuestionTXT;
import db.Savable;
import model.Category;
import model.Question;

import java.util.ArrayList;

public class DBContext implements DBStrategy {

    private DBStrategy strategy;

    public DBContext()
    {

    }


    public void setStrategy(DBStrategy strat)
    {
        this.strategy=strat;
    }


    @Override
    public void write() {
        strategy.write();
    }

    @Override
    public void read() {
        strategy.read();
    }

    public static void main(String args[])
    {
        DBContext context = new DBContext();
        ArrayList<Savable> categories = new ArrayList<>();
        Category c1 = new Category("Design principles","The SOLID design principles");
        Category c2 = new Category("Design patterns","A design pattern");
        Category c3 = new Category("UML","Make an UML");
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);

        ArrayList<Savable> questions = new ArrayList<>();
        Question q1;
        Question q2;

        ArrayList<String> answers_q1 = new ArrayList<>();
        ArrayList<String> answers_q2 = new ArrayList<>();

        answers_q1.add("Simple Factory");
        answers_q1.add("Singleton");
        answers_q1.add("Strategy");

        answers_q2.add("S");
        answers_q2.add("O");
        answers_q2.add("L");
        answers_q2.add("I");
        answers_q2.add("D");

        q1 = new Question("What pattern defines a family of algorithmes?", answers_q1, c2.getName(), "feedback", 5);
        q2 = new Question("What design principle has the least to do with Strategys?", answers_q2, c1.getName(), "feedback", 3);
        questions.add(q1);
        questions.add(q2);


        context.setStrategy(new QuestionTXT("TestDatabase\\QuestionFile.txt",questions));
        context.write();
        context.setStrategy(new CategoryTXT("TestDatabase\\CategoryFile.txt",categories));
        context.write();

    }
}



package db;

import model.Category;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        //Category c1 = new Category("Design principles","The SOLID design principles");
        //Category c2 = new Category("Design patterns","A design pattern");
        //Category c3 = new Category("UML","Make an UML");
        //categories.add(c1);
        //categories.add(c2);
        //categories.add(c3);

        context.setStrategy(new CategoryTXT("TestDatabase\\CategoryFile.txt",categories));
        context.write();

    }
}



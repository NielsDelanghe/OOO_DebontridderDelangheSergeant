package db;

import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryDB {

    private PrintWriter writer;
    private PrintWriter reader;
    private static CategoryDB uniqueInstance;
    private static File categoryFile;
    private List<Category> categoryList;

    public CategoryDB(ArrayList<Category> cats)
    {
        categoryFile = new File("TestDatabase\\CategoryFile.txt");
        categoryList=new ArrayList<>();
        try {
            writer = new PrintWriter(categoryFile);
            reader = new PrintWriter(categoryFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Bestant niet gevonden");
        }

        categoryList=cats;

    }

    public static CategoryDB getInstance(){
        if(uniqueInstance ==null)
        {
            uniqueInstance = new CategoryDB(new ArrayList<Category>());
        }
        return uniqueInstance;
    }

    public void writeToFile()
    {
        for(Category c : categoryList)
        {
            try{
                System.out.println("test");
                writer.println(c.toString());
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
        writer.close();
    }

    public void ReadFromFile()
    {

    }


}



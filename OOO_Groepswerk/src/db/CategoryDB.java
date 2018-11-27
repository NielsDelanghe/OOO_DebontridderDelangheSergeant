package db;

import model.Category;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CategoryDB {

    private FileWriter writer;
    private FileReader reader;
    private static CategoryDB uniqueInstance;
    private static File categoryFile;
    private List<Category> categoryList;

    public CategoryDB()
    {
        categoryFile = new File("CategoryFile.txt");
        categoryList=new ArrayList<>();
        try {
            writer = new FileWriter(categoryFile);
            reader = new FileReader(categoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CategoryDB getInstance(){
        if(uniqueInstance ==null)
        {
            uniqueInstance = new CategoryDB();
        }
        return uniqueInstance;
    }

    public void writeToFile()
    {
        for(Category c : categoryList)
        {
            try {
                writer.write(c.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[])
    {
        CategoryDB db;
        db = CategoryDB.getInstance();

        ArrayList<Category>categories = new ArrayList<>();
        Category c1 = new Category("Design principles","The SOLID design principles",4,"You should know the design of a principle");
        Category c2 = new Category("Design patterns","A design pattern",3,"Know all patterns");

        categories.add(c1);
        categories.add(c2);

        db.writeToFile();

    }

}



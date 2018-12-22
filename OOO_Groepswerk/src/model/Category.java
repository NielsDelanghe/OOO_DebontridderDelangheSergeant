package model;

import db.Savable;

public class Category implements Savable {

    private String name;
    private String description;
    private String mainCategory;


    public Category(String name, String description, String main)
    {
        this.setName(name);
        this.setDescription(description);
        this.setMainCategory(main);
    }


    public void setName(String name)
    {
        if(name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name of category can't be empty");
        }
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setDescription(String description)
    {
        if(description == null || description.trim().isEmpty())
        {
            throw new IllegalArgumentException("Description of category can't be empty");
        }
        this.description = description;

    }

    public String getDescription()
    {
        return this.description;
    }

    public String getMainCategory()
    {
        return mainCategory;
    }

    public void setMainCategory(String category)
    {
        mainCategory=category;
    }



    public String toString()
    {
       return name + "\t" + description + "\t" + mainCategory +"\n";
    }

    public static void main(String args[])
    {

        Category c2 = new Category("Test","test","Test");
        System.out.println(c2.toString());


    }

}

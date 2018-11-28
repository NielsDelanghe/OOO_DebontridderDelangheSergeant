package model;

import db.Savable;

public class Category implements Savable {

    private String name;
    private String description;
    private Category mainCategory;


    public Category(String name, String description, Category main)
    {
        this.setName(name);
        this.setDescription(description);
        this.setMainCategory(main);
    }

    public Category(String name,String description)
    {
        this.setName(name);
        this.setDescription(description);
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

    public Category getMainCategory()
    {
        return mainCategory;
    }

    public void setMainCategory(Category category)
    {
        mainCategory=category;
    }



    public String toString()
    {
       return name + "\t" + description + "\t";
    }

    public static void main(String args[])
    {
        Category c1;
        c1 = new Category("UML","uml tekening");
        c1.setMainCategory(c1);
        Category c2 = new Category("Test","test",c1);
        System.out.println(c2.toString());
        System.out.println(c1.toString());

    }

}

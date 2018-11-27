package model;

public class Category {

    private String name;
    private String description;

    public Category(String name, String description)
    {
        this.setName(name);
        this.setDescription(description);
    }

    private void setName(String name)
    {
        if(name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name of category can't be empty");
        }
        this.name = name;
    }

    private void setDescription(String description)
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

    public String getName()
    {
        return this.name;
    }

    public String toString()
    {
       return this.getName() + "\t" + this.getDescription() + "\n";
    }

}

package model;

public class Category {

    private String name;
    private String description;

    public Category(String n, String desc)
    {
        this.setName(n);
        this.setDescription(desc);
    }

    private void setName(String n)
    {
        if(name==null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name is empty");
        }
        else
            this.name=n;
    }

    public String getName()
    {
        return this.name;
    }

    private void setDescription(String desc)
    {
        if(desc==null || desc.trim().isEmpty())
        {
            throw new IllegalArgumentException("Description is empty");
        }

        else
            this.description=desc;

    }

    public String getDescription()
    {
        return this.description;
    }

    public String toString()
    {
       return name + "\t" + description + "\n";
    }

}

package model;

public class Category {

    private String name;
    private String description;
    private int points;
    private String feedback;

    public Category(String name, String description, int points, String feedback)
    {
        this.setName(name);
        this.setDescription(description);
        this.setPoints(points);
        this.setFeedback(feedback);
    }

    private void setName(String name)
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

    public void setFeedback(String feedback) {
        if(feedback == null || feedback.trim().length() == 0)
        {
            throw new IllegalArgumentException("Feedback of a category can't be empty");
        }
        this.feedback = feedback;
    }

    public void setPoints(int points) {
        if(points < 1)
        {
            throw new IllegalArgumentException("The points of a category should be at least 1");
        }
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getFeedback() {
        return feedback;
    }

    public String toString()
    {
       return name + "\t" + description + "\n";
    }

}

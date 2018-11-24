package view.observers;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> categories;

    public CategoryList()
    {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category)
    {
        if(categories.contains(category))
        {
            throw new IllegalArgumentException("Given category already exists");
        }

        else if(category == null)
        {
            throw new IllegalArgumentException("Category may not be empty");
        }

        else
        {
            categories.add(category);
        }
    }

    public String getCategotyList()
    {
        String list="";
        for(Category cat : categories)
        {
            list+=cat.toString();
        }
        return list;
    }

}


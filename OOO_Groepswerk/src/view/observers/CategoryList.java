package view.observers;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> categories;

    public CategoryList()
    {
        categories = new ArrayList<>();
        Category c1 = new Category("Design principles","The SOLID design principles",4,"You should know the design of a principle");
        Category c2 = new Category("Design patterns","A design pattern",3,"Know all patterns");
        categories.add(c1);
        categories.add(c2);
    }

    public void addCategory(Category category)
    {
        if(categories.contains(category))
        {
            throw new IllegalArgumentException("Given category already exists");
        }

        else if(category == null)
        {
            throw new IllegalArgumentException("Category can't be empty");
        }
        categories.add(category);
    }

    public List<Category> getCategotyList()
    {
        return this.categories;
    }

}


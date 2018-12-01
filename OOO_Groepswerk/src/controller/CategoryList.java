package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private ObservableList<Category> categories;

    public CategoryList()
    {
        categories = FXCollections.observableArrayList(new ArrayList<>());


        Category c1 = new Category("Design principles","The SOLID design principles");
        Category c2 = new Category("Design patterns","A design pattern");
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

    public ObservableList<Category> getCategotyList()
    {
        return this.categories;
    }

    public List<String> getCategoryNames()
    {
        List<String> names = new ArrayList<>();
        for(Category category : categories)
        {
            names.add(category.getName());
        }
        return names;
    }

}


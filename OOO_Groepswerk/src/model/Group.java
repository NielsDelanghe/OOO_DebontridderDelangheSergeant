package model;

import javafx.collections.ObservableList;

public class Group {
    private ObservableList<Category> categories;

    public void addcat(Category c){
        categories.add(c);
    }
    public ObservableList<Category> getList(){
        return categories;
    }
}

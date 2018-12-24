package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategoryTXT extends TXTDBStrategy {

    File file;
    ObservableList<Savable> categoryList = FXCollections.observableArrayList(new ArrayList<>());
    public void setCategoryList(ObservableList<Savable> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryTXT(ObservableList<Savable> list)
    {
        this.file = new File("resources/db/CategoryFile.txt");
        this.setCategoryList(list);
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public ObservableList<Savable> getObjectsToWrite() {
        return categoryList;
    }

    @Override
    public Savable convertStringToObject(String[] velden) {
        Savable object = new Category(velden[0],velden[1],velden[2]);
        return object;

    }


}

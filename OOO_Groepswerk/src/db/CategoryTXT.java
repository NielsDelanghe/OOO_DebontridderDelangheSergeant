package db;

import model.Category;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategoryTXT extends TXTDBStrategy {

    File file;
    ArrayList<Savable> categoryList = new ArrayList<>();

    public void setCategoryList(ArrayList<Savable> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryTXT(String path, ArrayList<Savable> list)
    {
        this.file=new File(path);
        this.setCategoryList(list);
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public List<Savable> getObjectsToWrite() {
        return categoryList;
    }

    @Override
    public Savable convertStringToObject(String[] velden) {
        Savable object = new Category(velden[0],velden[1]);
        return object;

    }

}

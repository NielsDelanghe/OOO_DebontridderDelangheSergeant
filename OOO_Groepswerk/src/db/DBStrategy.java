package db;

import javafx.collections.ObservableList;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.ArrayList;

public interface DBStrategy {

    void write();

    void read();

    ObservableList<Savable> getReadObjects();

    ArrayList<String> getCategoryTitles();

}

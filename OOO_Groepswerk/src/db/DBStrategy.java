package db;

import javafx.collections.ObservableList;

public interface DBStrategy {

    void write();

    void read();

   ObservableList<Savable> getReadObjects();

}

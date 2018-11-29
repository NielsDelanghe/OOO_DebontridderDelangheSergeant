package db;

import java.util.ArrayList;

public interface DBStrategy {

    void write();

    void read();

    ArrayList<Savable> getReadObjects();

}

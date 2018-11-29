package db;

import java.util.List;

public interface DBStrategy {

    void write();

    void read();

    List<Savable> getReadObjects();

}

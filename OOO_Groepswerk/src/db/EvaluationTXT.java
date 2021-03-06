package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Test;

import java.io.File;
import java.util.ArrayList;

public class EvaluationTXT extends TXTDBStrategy{

    File file;
    ObservableList<Savable> scoreList = FXCollections.observableArrayList(new ArrayList<>());

    public void setScoreList(ObservableList<Savable> scoreList) {
        this.scoreList = scoreList;
    }

    public EvaluationTXT( ObservableList<Savable> list)
    {
        this.file=new File("resources/db/Evaluation.txt");
        this.setScoreList(list);
    }

    @Override
    public String getFile() {
        return "resources/db/Evaluation.txt";
    }

    @Override
    public ObservableList<Savable> getObjectsToWrite() {
        return scoreList;
    }

    @Override
    public Savable convertStringToObject(String[] velden) {
        if(velden.length==0)
        {
            return null;
        }
        else
        {
            Savable object = new Test();
            ((Test) object).setScore(Integer.parseInt(velden[0]));
            ((Test) object).setMaxPossibleScore(Integer.parseInt(velden[1]));
            return object;
        }

    }

    @Override
    public String getWriteFile() {
        return "Evaluation.txt";
    }


}

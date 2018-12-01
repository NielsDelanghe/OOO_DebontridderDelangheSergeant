package db;

import model.Question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuestionTXT extends TXTDBStrategy {

        File file;
        ArrayList<Savable> questionList = new ArrayList<>();

        public QuestionTXT(String path, ArrayList<Savable> list)
        {
            this.file=new File(path);
            this.setCategoryList(list);
        }

    public void setCategoryList(ArrayList<Savable> qList) {
        this.questionList = qList;
    }

        @Override
        public File getFile() {
            return this.file;
        }

        @Override
        public List<Savable> getObjectsToWrite() {
            return questionList;
        }

        @Override
        public Savable convertStringToObject(String[] velden) {
            Savable object;

            List<String> statements = new ArrayList<>();

            for(int i=4; i < velden.length; i++)
            {
                statements.add(velden[i]);
            }

            object = new Question(velden[0],velden[1],velden[2],Integer.parseInt(velden[3]),statements);

            return object;
        }

}

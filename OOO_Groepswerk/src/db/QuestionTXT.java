package db;

import model.Question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuestionTXT extends TXTDBStrategy {

        File file;
        ArrayList<Savable> questionList = new ArrayList<>();
        ArrayList<Savable> readList = new ArrayList<>();

        public void setCategoryList(ArrayList<Savable> qList) {
            this.questionList = qList;
        }

        public QuestionTXT(String path, ArrayList<Savable> list)
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
            return questionList;
        }

    @Override
    public Savable convertStringToObject(String[] velden) {
            Savable object;
        List<String> statements = new ArrayList<>();

                object = new Question();
                ((Question) object).setQuestion(velden[0]);
                ((Question) object).setCategory(velden[1]);
                ((Question) object).setFeedback(velden[2]);
                ((Question) object).setPoints(Integer.parseInt(velden[3]));
                for(int i=4; i < velden.length; i++)
                {
                    statements.add(velden[i]);
                }
                ((Question) object).setPossible_answers(statements);

            return object;
    }

}

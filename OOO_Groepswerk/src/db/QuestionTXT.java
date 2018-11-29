package db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuestionTXT extends TXTDBStrategy {



        File file;
        ArrayList<Savable> categoryList = new ArrayList<>();

        public void setCategoryList(ArrayList<Savable> categoryList) {
            this.categoryList = categoryList;
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
            return categoryList;
        }

}

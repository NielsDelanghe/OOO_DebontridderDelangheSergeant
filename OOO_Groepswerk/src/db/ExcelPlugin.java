package db;

import controller.DBContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.Category;
import model.Question;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelPlugin {
    private WritableWorkbook workbook;

    public ExcelPlugin(File file) throws IOException{
        workbook = Workbook.createWorkbook(file);
    }

    private void writeExcel() throws WriteException, IOException{
        workbook.write();
        workbook.close();
    }

    public void writeCategoriesAndQuestionsToExcel(ObservableList<Savable> categories, ObservableList<Savable> questions) throws IOException, WriteException {

        ArrayList<ArrayList<String>> list = new ArrayList();

        for(int i = 0; i <categories.size(); i++)
        {
            ArrayList <String> rij = new ArrayList();
            rij.add(((Category) categories.get(i)).getName()); rij.add(((Category) categories.get(i)).getDescription()); rij.add(((Category) categories.get(i)).getMainCategory());
            list.add(rij);
        }

        try{
            this.addSheet(list, 0, "categories");
        }
        catch (Exception e){
            System.out.println("blad 1" + e.getMessage());
        }

        list = new ArrayList();

        for(int i=0; i<questions.size();i++)
        {
            ArrayList <String> rij = new ArrayList();
            rij.add(((Question) questions.get(i)).getQuestion()); rij.add(((Question) questions.get(i)).getCategory()); rij.add(((Question) questions.get(i)).getFeedback());

            int points = ((Question) questions.get(i)).getPoints();
            rij.add(Integer.toString(points));

            boolean correct = ((Question) questions.get(i)).getCorrect();
            rij.add(Boolean.toString(correct));

            for(String answer :((Question) questions.get(i)).getPossible_answers())
            {
                rij.add(answer);
            }

            list.add(rij);
        }
        try{
            this.addSheet(list,1,"questions");
        }
        catch (Exception e){
            System.out.println("blad 1" + e.getMessage());
        }
        try {
            this.writeExcel();
        }
        catch (Exception e){System.out.println("creatie excel " + e.getMessage());	}
    }

    public ObservableList<Savable> readCategoriesFromExcel(File file,int sheetNr)throws BiffException, IOException {
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetNr);
        int row = 0;
        ObservableList<Savable> categories = FXCollections.observableArrayList(new ArrayList<>());

        while(row < sheet.getRows())
        {
            int column = 0;
            ArrayList<String> rowinfo = new ArrayList<String>();
            while(column < sheet.getColumns()){
                Cell cell = sheet.getCell(column,row);
                String information = cell.getContents();
                rowinfo.add(information);
                column++;
            }
            Category category = new Category(rowinfo.get(0),rowinfo.get(1),rowinfo.get(2));
            categories.add(category);
            row++;

        }
        workbook.close();

        return categories;
    }


    public ObservableList<Savable> readQuestionsFromExcel(File file, int sheetNr) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetNr);
        int row = 0;
        ObservableList<Savable> questions = FXCollections.observableArrayList(new ArrayList<>());

        while(row < sheet.getRows())
        {
            int column = 0;
            ArrayList<String> rowinfo = new ArrayList<String>();
            while(column < sheet.getColumns()){
                Cell cell = sheet.getCell(column,row);
                String information = cell.getContents();
                rowinfo.add(information);
                column++;
            }

            ArrayList<String> possible_answers = new ArrayList<>();
            for(int i=5; i<rowinfo.size(); i++)
            {
                String answer =rowinfo.get(i);
                possible_answers.add(answer);
            }

            Question question = new Question(rowinfo.get(0),rowinfo.get(1),rowinfo.get(2),Integer.parseInt(rowinfo.get(3)),Boolean.valueOf(rowinfo.get(4)),possible_answers);
            questions.add(question);
            row++;

        }
        workbook.close();

        return questions;
    }


    public void addSheet(ArrayList<ArrayList<String>> args, int sheetNr, String sheetName)
            throws BiffException, RowsExceededException, WriteException{
        workbook.createSheet(sheetName, sheetNr);
        WritableSheet sheet = workbook.getSheet(sheetNr);
        for(int i = 0; i < args.size(); i++){
            ArrayList<String> parameters = args.get(i);
            for(int j = 0; j < parameters.size(); j++){
                Label label = new Label(j,i,parameters.get(j));
                sheet.addCell(label);
            }
        }
    }

    private static ArrayList<ArrayList<String>> readExcel(File file,int sheetNr)
            throws BiffException, IOException {
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetNr);
        int row = 0;

        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        while(row < sheet.getRows())
        {
            int column = 0;
            ArrayList<String> rowinfo = new ArrayList<String>();
            while(column < sheet.getColumns()){
                Cell cell = sheet.getCell(column,row);
                String information = cell.getContents();
                rowinfo.add(information);
                column++;
            }
            info.add(rowinfo);
            row++;
        }
        workbook.close();
        return info;
    }

    public static void main (String [] args) throws IOException, WriteException, BiffException {
        ObservableList<Savable>savables = FXCollections.observableArrayList(new ArrayList<>());
        DBContext context;
        context = new DBContext();

        context.setStrategy(new QuestionTXT(savables));
        context.read();

        ObservableList<Savable> questionArrayList = context.getReadObjects();

        context.setStrategy(new CategoryTXT(savables));
        context.read();

        ObservableList<Savable> categoryArrayList = context.getReadObjects();

        ExcelPlugin plugin = new ExcelPlugin(new File("test.xls"));
        plugin.writeCategoriesAndQuestionsToExcel(categoryArrayList,questionArrayList);


    }
}
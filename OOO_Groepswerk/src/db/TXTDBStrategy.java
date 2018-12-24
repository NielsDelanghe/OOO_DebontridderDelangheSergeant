package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class TXTDBStrategy implements DBStrategy {

    private File file;
    private PrintWriter writer;
    private ObservableList<Savable>savables = FXCollections.observableArrayList(new ArrayList<>());
    private InputStream inputStream;
    @Override
    public void write() {

        file = new File(getWriteFile());
        try {
             writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int counter =0;
        while (counter <getObjectsToWrite().size())
        {
            writer.print(getObjectsToWrite().get(counter));
            counter++;
        }
        writer.close();

    }

    @Override
    public void read() {

        inputStream = getClass().getClassLoader().getResourceAsStream(getFile());
        file = new File(getWriteFile());
        if(file.exists() && !file.isDirectory()){
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            Scanner scannerFile;
            scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                String lijn = scannerFile.nextLine();
                if (!lijn.isEmpty()) {
                    String[] velden = lijn.split("\t");
                    savables.add(convertStringToObject(velden));
                }

            }
            scannerFile.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Savable> getReadObjects(){

        return savables;
    }

    public abstract String getFile();

    public abstract List<Savable> getObjectsToWrite();

    public abstract Savable convertStringToObject(String [] velden);

    public abstract String getWriteFile();

    @Override
    public ArrayList<String> getCategoryTitles()
    {
        ArrayList<String> titles = new ArrayList<>();

        String out="";

        for (Savable savable : savables)
        {
            if(savable instanceof Category)
            {
                out=((Category) savable).getName()+"\n";
                titles.add(out);
            }
        }
        return titles;
    }







}

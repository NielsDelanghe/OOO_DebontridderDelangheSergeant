package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public abstract class TXTDBStrategy implements DBStrategy {

    private File file;
    private PrintWriter writer;

    @Override
    public void write() {

        file = getFile();
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
        file=getFile();
        Scanner scannerFile;
        try {
            scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {

                String lijn = scannerFile.nextLine();
                String [] velden = lijn.split("\t");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public abstract File getFile();

    public abstract List<Savable> getObjectsToWrite();

    //public abstract






}

package Extractor;

import Data.DataManager;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import Logger.MyLogger;
public class CsvExtractor extends FileExtractor {

    public CsvExtractor(String path) {
        super(path);
    }

    @Override
    public void extract() {
        Logger logger = new MyLogger().getLOGGER();
        try {
            CSVReader reader = new CSVReader(new BufferedReader(new FileReader(path)));
            String[] parameters = reader.readNext();
            this.data = new DataManager(parameters);
            String[] newRecord;
            while ((newRecord = reader.readNext()) != null) {
                this.data.addRecord(newRecord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DataManager getData() {
        return this.data;
    }


}

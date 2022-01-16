package Extractor;

import Data.DataManager;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvExtractor implements Extractor {
    private String path;
    private DataManager data;

    public CsvExtractor(String path) {
        this.path = path;
    }

    @Override
    public void extract() {
        try {
            CSVReader reader = new CSVReader(new FileReader(this.path));
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

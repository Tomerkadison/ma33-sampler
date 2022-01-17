package Extractor;

import Data.DataManager;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Extractor {
    protected DataManager data;

    public abstract void extract();

    public DataManager getData() {
        return data;
    }
}

package Extractor;

import Data.DataManager;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Extractor {
    void extract();
    DataManager getData();
}

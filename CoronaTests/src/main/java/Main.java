import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.FileLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/filesProperties.properties"));
            CsvExtractor csvExtractor = new CsvExtractor(properties.getProperty("readingPath"));
            FileLoader basicLoader = new FileLoader(properties.getProperty("writingPath"), "json");
            BasicETL etl = new BasicETL(csvExtractor, basicLoader);
            etl.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

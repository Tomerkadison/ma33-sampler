import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.FileLoader;
import Loader.Writer.JsonWriter.JsonWriter;
import Loader.Writer.XmlWriter.XmlWriter;
import Transformer.LabTestsTransformer;
import Transformer.Transformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/filesProperties.properties"));
            CsvExtractor csvExtractor = new CsvExtractor(properties.getProperty("labTestsReadingPath"));
            FileLoader basicLoader = new FileLoader(new XmlWriter(properties.getProperty("labTestsWritingPath"), "labTest"));
            FileLoader basicLoader2 = new FileLoader(new JsonWriter(properties.getProperty("labTestsWritingPath")));
            Transformer transformer = new LabTestsTransformer();
            BasicETL etl = new BasicETL(csvExtractor, transformer,basicLoader);
            BasicETL etl1 = new BasicETL(csvExtractor,transformer,basicLoader2);
            etl.execute();
            etl1.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

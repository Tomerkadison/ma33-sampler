import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.FileLoader;
import Loader.Writer.JsonWriter.JsonWriter;
import Loader.Writer.XmlWriter.XmlWriter;
import Transformer.CrossDataTransformer.CrossDataTransformer;
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
            FileLoader loader = new FileLoader(new JsonWriter(properties.getProperty("positiveCoronaPeopleWritingPath")));
            CsvExtractor labTestsExtractor = new CsvExtractor(properties.getProperty("labTestsReadingPath"));
            CsvExtractor madaReportsExtractor = new CsvExtractor(properties.getProperty("madaReportsReadingPath"));
            madaReportsExtractor.extract();
            Transformer transformer = new CrossDataTransformer(
                    madaReportsExtractor.getData(),
                    "IDNum",
                    new String[]{"IDNum", "IDType", "FirstName", "LastName", "City", "Street", "BuildingNumber", "Barcode", "BirthDate"
                            , "LabCode", "ResultDate", "TakeDate", "StickerNumber", "ResultTestCorona", "Variant", "TestType"});
            BasicETL etl = new BasicETL(labTestsExtractor, transformer, loader);
            etl.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

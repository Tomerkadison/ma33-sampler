import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.FileLoader;
import Loader.Writer.JsonWriter.JsonWriter;
import Loader.SqlLoader;
import Transformer.CrossDataTransformer.CrossDataTransformer;
import Transformer.Transformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import Transformer.*;
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        SqlLoader loader = new SqlLoader("jdbc:sqlserver://localhost/SQLEXPRESS;databaseName=LabResults","LabResults_SerologyKits",getTypes());
        CsvExtractor labResultsExtractor = new CsvExtractor(properties.getProperty("labResultsReadingPath"));
        Transformer transformer = new LabResultsValidation();
        BasicETL etl = new BasicETL(labResultsExtractor, transformer, loader);
        etl.execute();
    }
    public static HashMap<String,String> getTypes(){
        HashMap<String,String> types = new HashMap<>();
        types.put("IDNum","INT");
        types.put("IDType","INT");
        types.put("FirstName","VARCHAR(255)");
        types.put("LastName","VARCHAR(255)");
        types.put("ResultDate","DATE");
        types.put("BirthDate","DATE");
        types.put("LabCode","VARCHAR(5)");
        return types;
    }
}

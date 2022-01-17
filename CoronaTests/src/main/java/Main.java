import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.BasicLoader;

public class Main {
    public static void main(String[] args) {
        CsvExtractor csvExtractor = new CsvExtractor("src/main/resources/MadaReports.csv");
        BasicLoader basicLoader = new BasicLoader("C:\\Users\\kadis\\Desktop\\temp\\tests", "json");
        BasicETL etl = new BasicETL(csvExtractor, basicLoader);
        etl.execute();
    }
}

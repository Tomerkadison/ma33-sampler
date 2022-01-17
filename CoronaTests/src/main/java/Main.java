import ETL.BasicETL;
import Extractor.CsvExtractor;
import Loader.FileLoader;

public class Main {
    public static void main(String[] args) {
        CsvExtractor csvExtractor = new CsvExtractor("src/main/resources/MadaReports.csv");
        FileLoader basicLoader = new FileLoader("C:\\Users\\kadis\\Desktop\\temp\\mada_reports", "json");
        BasicETL etl = new BasicETL(csvExtractor, basicLoader);
        etl.execute();
    }
}

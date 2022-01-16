import ETL.ETL;
import Extractor.CsvExtractor;
import Loader.BasicLoader;

public class Main {
    public static void main(String[] args) {
        CsvExtractor csvExtractor= new CsvExtractor("src/main/resources/MadaReports.csv");
        BasicLoader basicLoader = new BasicLoader("C:\\Users\\kadis\\Desktop\\temp\\tests","json");
        ETL etl = new ETL(csvExtractor,basicLoader);
        etl.execute();
    }
}

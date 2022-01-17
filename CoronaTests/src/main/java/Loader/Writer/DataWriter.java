package Loader.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DataWriter {
    void writeRecords(List<HashMap<String, String>> records);
}

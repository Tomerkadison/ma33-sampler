package Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    private ArrayList<HashMap<String, String>> data;
    private String[] parameters;

    public Data(String[] parameters) {
        this.parameters = parameters;
        this.data = new ArrayList<HashMap<String, String>>();
    }

    public void addRecord(String[] record) {
        HashMap<String, String> newEntity = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            newEntity.put(parameters[i], record[i]);
        }
        this.data.add(newEntity);
    }
    public void addColumn(String columnName){
        String[] oldParameters = this.parameters.clone();
        this.parameters = new String[oldParameters.length+1];
        for(int i = 0;i<oldParameters.length;i++){
            this.parameters[i] = oldParameters[i];
        }
        this.parameters[this.parameters.length -1] = columnName;
    }
}

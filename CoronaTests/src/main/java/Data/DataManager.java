package Data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private ArrayList<HashMap<String, String>> data;
    private String[] parameters;

    public DataManager(String[] parameters) {
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

    public HashMap<String,String> getRecord(int i){
        return this.data.get(i);
    }

    public void addColumn(String columnName){
        String[] oldParameters = this.parameters.clone();
        this.parameters = new String[oldParameters.length+1];
        for(int i = 0;i<oldParameters.length;i++){
            this.parameters[i] = oldParameters[i];
        }
        this.parameters[this.parameters.length -1] = columnName;
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    public String[] getParameters() {
        return parameters;
    }
}

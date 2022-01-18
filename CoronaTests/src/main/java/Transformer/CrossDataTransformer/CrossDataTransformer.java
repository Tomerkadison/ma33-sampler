package Transformer.CrossDataTransformer;

import Data.DataManager;
import Logger.MyLogger;
import Transformer.Transformer;

import java.util.HashMap;
import java.util.logging.Level;

public class CrossDataTransformer extends Transformer {
    private String[] parameters;
    private DataManager otherData;
    private String primaryKey;

    public CrossDataTransformer(DataManager otherData, String primaryKey, String[] parameters) {
        this.otherData = otherData;
        this.parameters = parameters;
        this.primaryKey = primaryKey;
    }

    @Override
    public void Transform() {
        DataManager newData = new DataManager(this.parameters);
        MyLogger myLogger = new MyLogger();
        HashMap<String, Integer> idToIndexMap = getIdToIndexMap();
        for (HashMap<String, String> record : this.dataManager.getData()) {
            if (idToIndexMap.containsKey(record.get(this.primaryKey))) {
                int index = idToIndexMap.get(record.get(this.primaryKey));
                addNewDataRecord(newData, record, this.otherData.getData().get(index));
            }else{
                myLogger.getLogger().log(Level.WARNING,primaryKey + ": "+record.get(primaryKey) +" wasn't found in the other data structure.");
            }
        }
        this.dataManager = newData;
    }

    public HashMap<String, Integer> getIdToIndexMap(){
        HashMap<String, Integer> idToIndexMap = new HashMap<>();
        for (int i = 0; i < this.otherData.getData().size(); i++) {
            idToIndexMap.put(this.otherData.getData().get(i).get(this.primaryKey), i);
        }
        return idToIndexMap;
    }

    public void addNewDataRecord(DataManager newData, HashMap<String, String> record, HashMap<String, String> otherRecord) {
        HashMap<String, String> newRecord = new HashMap<>();
        for (String parameter : this.parameters) {
            if (record.containsKey(parameter)) {
                newRecord.put(parameter, record.get(parameter));
            } else if (otherRecord.containsKey(parameter)) {
                newRecord.put(parameter, otherRecord.get(parameter));
            } else {
                ParameterNotFoundException e = new ParameterNotFoundException("The " + parameter + " parameter isn't a parameter of neither data structures");
                e.printStackTrace();
                System.exit(1);
            }
        }
        newData.getData().add(newRecord);
    }

}

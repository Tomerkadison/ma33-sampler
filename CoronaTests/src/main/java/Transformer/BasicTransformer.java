package Transformer;

import Data.DataManager;

public abstract class BasicTransformer implements Transformer {
    private DataManager dataManager;

    public void setData(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getData() {
        return dataManager;
    }

}

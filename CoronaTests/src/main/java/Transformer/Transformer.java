package Transformer;

import Data.DataManager;

public abstract class Transformer {
    private DataManager dataManager;

    public void setData(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getData() {
        return dataManager;
    }

    public abstract void Transform();

}

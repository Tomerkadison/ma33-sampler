package Transformer;

import Data.DataManager;

public abstract class Transformer {
    protected DataManager dataManager;

    public void setData(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public abstract void Transform();

}

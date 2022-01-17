package Loader;

import Data.DataManager;

public abstract class Loader {
    protected DataManager data;

    public abstract void load();

    public void setData(DataManager data) {
        this.data = data;
    }
}

package Loader;

import Data.Data;

public class BasicLoader implements Loader {
    private String path;
    private Data data;
    public BasicLoader(Data data) {
        this.data = data;
    }

    @Override
    public void load() {

    }
}

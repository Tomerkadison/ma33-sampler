package Extractor;

import Data.DataManager;

public abstract class BasicExtractor implements Extractor {
    protected String path;
    protected DataManager data;

    public BasicExtractor(String path) {
        this.path = path;
    }
    public abstract DataManager getData();
}

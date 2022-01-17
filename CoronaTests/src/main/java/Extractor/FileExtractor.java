package Extractor;

import Data.DataManager;

public abstract class FileExtractor extends Extractor {
    protected String path;
    protected DataManager data;

    public FileExtractor(String path) {
        this.path = path;
    }

}

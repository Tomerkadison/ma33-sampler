package Loader.Writer;

import java.util.HashMap;
import java.util.List;

public abstract class FileDataWriter implements DataWriter {
    protected String path;

    public FileDataWriter(String path) {
        this.path = path;
    }
}

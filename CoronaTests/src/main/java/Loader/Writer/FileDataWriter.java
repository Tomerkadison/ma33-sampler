package Loader.Writer;

import java.util.HashMap;
import java.util.List;

public abstract class FileDataWriter implements DataWriter {
    protected String path;
    protected String type;

    public FileDataWriter(String path) {
        this.path = path;
        this.type = setType();
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    abstract protected String setType();

}

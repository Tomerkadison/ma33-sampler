package Loader.Writer.Factory;

import Loader.Writer.DataWriter;
import Loader.Writer.Factory.DataWriterFactory;
import Loader.Writer.JsonWriter;

public class JsonWriterFactory implements DataWriterFactory {
    private String path;

    public JsonWriterFactory(String path) {
        this.path = path;
    }

    @Override
    public DataWriter create() {
        return new JsonWriter(this.path);
    }
}

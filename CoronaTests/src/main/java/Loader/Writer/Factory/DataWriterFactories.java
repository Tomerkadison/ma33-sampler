package Loader.Writer.Factory;

import Loader.Writer.JsonWriter.JsonWriterFactory;

import java.util.HashMap;

public class DataWriterFactories {
    private HashMap<String, DataWriterFactory> types;

    public DataWriterFactories(String path) {
        this.types = new HashMap<>();
        this.types.put("json", new JsonWriterFactory(path));
    }

    public DataWriterFactory get(String type) {
        return this.types.get(type);
    }
}

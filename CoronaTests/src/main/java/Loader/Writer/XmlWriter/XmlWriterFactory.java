package Loader.Writer.XmlWriter;

import Loader.Writer.DataWriter;
import Loader.Writer.Factory.DataWriterFactory;

public class XmlWriterFactory implements DataWriterFactory {
    private String path;

    public XmlWriterFactory(String path) {
        this.path = path;
    }

    @Override
    public DataWriter create() {
        return new XmlWriter(this.path);
    }
}

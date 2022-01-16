package Loader;

import Data.DataManager;
import Loader.Writer.DataWriter;
import Loader.Writer.Factory.DataWriterFactories;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class BasicLoader implements Loader {
    private String path;
    private DataManager data;
    private String type;
    private DataWriter dataWriter;

    public BasicLoader(String path, String type) {
        this.path = path;
        this.type = type;
        this.dataWriter = new DataWriterFactories(path).get(type).create();
    }

    @Override
    public void load() {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < this.data.getData().size() / 50000; i++) {
            dataWriter.writeRecords(this.data.getRecords(i*50000,(i+1)*50000));
            this.path = this.path.substring(0,this.path.length()-this.type.length()-1) + "l."+type;
        }
        this.dataWriter.writeRecords(this.data.getRecords(5000*(this.data.getData().size() / 50000),this.data.getData().size() -1));
    }
    public void setData(DataManager data){
        this.data = data;
    }
}

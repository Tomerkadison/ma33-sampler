package Loader;

import Data.DataManager;
import Loader.Writer.DataWriter;
import Loader.Writer.Factory.DataWriterFactories;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicLoader implements Loader {
    private String path;
    private DataManager data;
    private String type;
    private DataWriter dataWriter;

    public BasicLoader(String path, String type) {
        this.type = type;
        this.path = path +"."+ type;
        this.dataWriter = new DataWriterFactories(this.path).get(this.type).create();
    }

    @Override
    public void load() {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i <(this.data.getData().size()-1)/ 500; i++) {
            dataWriter.writeRecords(this.data.getRecords(i*100,(i+1)*100));
            this.path = this.path.substring(0,this.path.length()-this.type.length()-1) + "l."+type;
            this.dataWriter = new DataWriterFactories(this.path).get(this.type).create();
        }
        this.dataWriter.writeRecords(this.data.getRecords(100*((this.data.getData().size()-1) / 100),this.data.getData().size()));
    }
    public void setData(DataManager data){
        this.data = data;
    }
}

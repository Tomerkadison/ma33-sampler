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

public class FileLoader extends Loader {
    private String path;
    private String type;
    private DataWriter dataWriter;
    private static final int amountInFile = 50000;

    public FileLoader(String path, String type) {
        this.type = type;
        this.path = path + "." + type;
        this.dataWriter = new DataWriterFactories(this.path).get(this.type).create();
    }

    @Override
    public void load() {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < (this.data.getData().size() - 1) / amountInFile; i++) {
            dataWriter.writeRecords(this.data.getRecords(i * amountInFile, (i + 1) * amountInFile));
            this.path = this.path.substring(0, this.path.length() - this.type.length() - 1) + "l." + type;
            this.dataWriter = new DataWriterFactories(this.path).get(this.type).create();
        }
        this.dataWriter.writeRecords(this.data.getRecords(amountInFile * ((this.data.getData().size() - 1) / amountInFile), this.data.getData().size()));
    }
}

package Loader;

import Data.DataManager;
import Loader.Writer.DataWriter;
import Loader.Writer.Factory.DataWriterFactories;

public class BasicLoader implements Loader {
    private String path;
    private DataManager data;
    private String type;
    private DataWriter dataWriter;
    public BasicLoader(String path,String type, DataManager data) {
        this.path = path;
        this.data = data;
        this.type = type;
        this.dataWriter = new DataWriterFactories(path).get(type).create();
    }

    @Override
    public void load() {
        for(int i = 0;i<data.getData().size();i++){
            if(i+1 %5000 == 0)
            {
                this.path = this.path.substring(0,this.path.length()- type.length() + 1) + "l." + type;
                this.dataWriter = new DataWriterFactories(this.path).get(this.type).create();
            }
            this.dataWriter.writeRecord(this.data.getRecord(i));
        }

    }
}

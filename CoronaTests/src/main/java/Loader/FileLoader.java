package Loader;
import Loader.Writer.FileDataWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class FileLoader extends Loader {
    private String path;
    private String type;
    private FileDataWriter dataWriter;
    private static final int amountInFile = 50000;

    public FileLoader(FileDataWriter dataWriter) {
        this.dataWriter = dataWriter;
        this.type = this.dataWriter.getType();
        this.path = dataWriter.getPath() + "." + type;
        this.dataWriter.setPath(this.path);

    }

    @Override
    public void load() {
        Logger logger = Logger.getLogger(FileLoader.class.getName());
        logger.addHandler(new ConsoleHandler());
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < (this.data.getData().size() - 1) / amountInFile; i++) {
            logger.info("writing a new data file in " + path);
            dataWriter.writeRecords(this.data.getRecords(i * amountInFile, (i + 1) * amountInFile));
            this.path = this.path.substring(0, this.path.length() - this.type.length() - 1) + "l." + type;
            this.dataWriter.setPath(this.path);
        }
        this.dataWriter.writeRecords(this.data.getRecords(amountInFile * ((this.data.getData().size() - 1) / amountInFile), this.data.getData().size()));
    }
}

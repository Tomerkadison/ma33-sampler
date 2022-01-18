package Loader;
import Loader.Writer.FileDataWriter;
import Logger.MyLogger;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoader extends Loader {
    private String path;
    private String type;
    private FileDataWriter fileDataWriter;
    private static final int amountInFile = 100;

    public FileLoader(FileDataWriter dataWriter) {
        this.fileDataWriter = dataWriter;
        this.type = this.fileDataWriter.getType();
        this.path = dataWriter.getPath() + "." + type;
        this.fileDataWriter.setPath(this.path);

    }

    @Override
    public void load() {
        Logger logger = new MyLogger().getLOGGER();
        for (int i = 0; i < (this.data.getData().size() - 1) / amountInFile; i++) {
            logger.log(Level.INFO,String.format("writing records %s - %s",i * amountInFile,(i + 1) * amountInFile));
            fileDataWriter.writeRecords(this.data.getRecords(i * amountInFile, (i + 1) * amountInFile));
            this.path = this.path.substring(0, this.path.length() - this.type.length() - 1) + "l." + type;
            this.fileDataWriter.setPath(this.path);
        }
        logger.log(Level.INFO,String.format("writing records %s - %s",
                amountInFile * ((this.data.getData().size() - 1) / amountInFile),
                this.data.getData().size()));
        this.fileDataWriter.writeRecords(this.data.getRecords(
                amountInFile * ((this.data.getData().size() - 1) / amountInFile)
                , this.data.getData().size()));
    }
}

package Loader;

import Loader.Writer.FileDataWriter;
import Logger.MyLogger;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoader extends Loader {
    private String path;
    private String type;
    private FileDataWriter fileDataWriter;
    private int amountInFile;
    private int maxSize = 20000000;
    public FileLoader(FileDataWriter dataWriter) {
        this.fileDataWriter = dataWriter;
        this.type = this.fileDataWriter.getType();
        this.path = dataWriter.getPath() + "." + type;
        this.fileDataWriter.setPath(this.path);

    }

    @Override
    public void load() {
        Logger logger = new MyLogger().getLogger();
        amountInFile = (int) (maxSize/this.findRecordSize());
        for (int i = 0; i < (this.data.getData().size() - 1) / amountInFile; i++) {
            logger.log(Level.INFO, String.format("writing records %s - %s to %s.", i * amountInFile, (i + 1) * amountInFile, this.path));
            fileDataWriter.writeRecords(this.data.getRecords(i * amountInFile, (i + 1) * amountInFile));
            this.path = this.path.substring(0, this.path.length() - this.type.length() - 1) + "l." + type;
            this.fileDataWriter.setPath(this.path);
        }
        logger.log(Level.INFO, String.format("writing records %s - %s to %s.",
                amountInFile * ((this.data.getData().size() - 1) / amountInFile),
                this.data.getData().size(),
                this.path));
        this.fileDataWriter.writeRecords(this.data.getRecords(
                amountInFile * ((this.data.getData().size() - 1) / amountInFile)
                , this.data.getData().size()));
    }

    public long findRecordSize(){
        fileDataWriter.writeRecords(this.data.getRecords(0,1));
        this.data.getData().remove(0);
        long size = new File(fileDataWriter.getPath()).length();
        return new File(fileDataWriter.getPath()).length();
    }
}

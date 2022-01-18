package ETL;

import Extractor.*;
import Loader.*;
import Transformer.*;
import Logger.MyLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicETL implements ETL {
    private Extractor extractor;
    private Transformer transformer;
    private Loader loader;

    public BasicETL(Extractor extractor, Transformer transformer, Loader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    public BasicETL(Extractor extractor, Loader loader) {
        this.extractor = extractor;
        this.transformer = new NoTransformTransformer();
        this.loader = loader;
    }

    @Override
    public void execute() {
        Logger logger = new MyLogger().getLOGGER();
        logger.log(Level.INFO,"Extracting");
        this.extractor.extract();
        this.transformer.setData(this.extractor.getData());
        logger.log(Level.INFO,"Transforming");
        this.transformer.Transform();
        this.loader.setData(transformer.getDataManager());
        logger.log(Level.INFO,"Loading");
        this.loader.load();
    }
}

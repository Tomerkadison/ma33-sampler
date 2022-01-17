package ETL;

import Extractor.*;
import Loader.*;
import Transformer.*;

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
        this.extractor.extract();
        this.transformer.setData(this.extractor.getData());
        this.transformer.Transform();
        this.loader.setData(transformer.getDataManager());
        this.loader.load();
    }
}

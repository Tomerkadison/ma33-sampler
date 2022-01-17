package ETL;

import Extractor.*;
import Loader.*;
import Transformer.*;

public class BasicETL implements ETL {
    private BasicExtractor extractor;
    private BasicTransformer transformer;
    private BasicLoader loader;

    public BasicETL(BasicLoader loader, BasicTransformer transformer, BasicExtractor extractor) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    public BasicETL(BasicExtractor extractor, BasicLoader loader) {
        this.extractor = extractor;
        this.transformer = new NoTransformTransformer();
        this.loader = loader;
    }

    @Override
    public void execute() {
        this.extractor.extract();
        this.transformer.setData(this.extractor.getData());
        this.transformer.Transform();
        this.loader.setData(transformer.getData());
        this.loader.load();
    }
}

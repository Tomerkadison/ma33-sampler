package ETL;

import Extractor.*;
import Loader.*;
import Transformer.*;

public class ETL {
    private BasicExtractor extractor;
    private BasicTransformer transformer;
    private BasicLoader loader;

    public ETL(BasicLoader loader,BasicTransformer transformer,BasicExtractor extractor) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }
    public ETL(BasicExtractor extractor ,BasicLoader loader) {
        this.extractor = extractor;
        this.transformer = new NoTransformTransformer();
        this.loader = loader;
    }

    public void execute(){
        this.extractor.extract();
        this.transformer.setData(this.extractor.getData());
        this.transformer.Transform();
        this.loader.setData(transformer.getData());
        this.loader.load();
    }
}

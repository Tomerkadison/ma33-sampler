package ETL;

import Extractor.Extractor;
import Loader.Loader;
import Transformer.*;

public class ETL {
    private Extractor extractor;
    private Transformer transformer;
    private Loader loader;

    public ETL(Extractor extractor, Transformer transformer,Loader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }
    public ETL(Extractor extractor,Loader loader) {
        this.extractor = extractor;
        this.transformer = new NoTransformTransformer();
        this.loader = loader;
    }

    public void execute(){
        this.extractor.extract();
        this.transformer.Transform();
        this.loader.load();
    }
}

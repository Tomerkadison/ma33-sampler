package Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private final Logger logger =
            Logger.getLogger("com.javacodegeeks.snippets.core");

    public Logger getLogger() {
        return logger;
    }
}

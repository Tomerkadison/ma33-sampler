package Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
    private final Logger logger =
            Logger.getLogger("com.javacodegeeks.snippets.core");
    public MyLogger() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/filesProperties.properties"));
            FileHandler handler = new FileHandler(properties.getProperty("logsWritingPath"), true);
            Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return  logger;

    }
}

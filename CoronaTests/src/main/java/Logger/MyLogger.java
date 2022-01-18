package Logger;

import java.util.logging.Logger;

public class MyLogger {
    private final Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Logger getLOGGER() {
        return LOGGER;
    }
}

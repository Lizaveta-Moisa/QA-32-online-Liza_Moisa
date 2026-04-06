package common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    public static Logger log() {
        return LogManager.getLogger();
    }
}

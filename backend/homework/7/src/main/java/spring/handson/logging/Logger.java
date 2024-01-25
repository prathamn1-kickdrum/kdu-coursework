package spring.handson.logging;

import org.slf4j.LoggerFactory;

/**
 * logger class stores shows log on console as well as stores in log folder
 */

@SuppressWarnings("all")
public class Logger {

    private static final Logger loggerObj = new Logger();  // private static instance

    // Private constructor to prevent instantiation from outside
    private Logger() {
    }

    // Method to get the singleton instance
    public static Logger getLoggerObject() {
        return loggerObj;
    }

    private static final org.slf4j.Logger loggerVar = LoggerFactory.getLogger(Logger.class);

    public void userPrompt(String msg) {
        loggerVar.info(msg);
    }

    public void debugLog(String msg) {
        loggerVar.debug(msg);
    }

    public void debugLog(boolean msg) {
        if(msg) loggerVar.debug("Booked");
        else loggerVar.debug("Not Booked");
    }

    public void debugLog(String msg, Exception e) {
        loggerVar.debug(msg, e);
    }

    public void errorLog(String msg) {
        loggerVar.error(msg);
    }

    public void errorLog(String msg, Exception e) {
        loggerVar.error(msg, e);
    }

    public void infoLog(String msg) {loggerVar.info(msg);}
}

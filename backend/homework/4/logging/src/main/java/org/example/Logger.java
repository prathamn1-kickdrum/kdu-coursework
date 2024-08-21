package org.example;

import org.slf4j.LoggerFactory;

public class Logger {

    private static Logger loggerObj;  // private static instance

    // Private constructor to prevent instantiation from outside
    private Logger() {
    }

    // Method to get the singleton instance
    public static Logger getLoggerObject() {
        if (loggerObj == null) {
            // If instance is not created, create one
            loggerObj = new Logger();
        }
        return loggerObj;
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public void userPrompt(String msg) {
        logger.info(msg);
    }

    public void debugLog(String msg) {
        logger.debug(msg);
    }

    public void debugLog(boolean msg) {
        if(msg) logger.debug("Booked");
        else logger.debug("Not Booked");
    }

    public void debugLog(String msg, Exception e) {
        logger.debug(msg, e);
    }

    public void errorLog(String msg) {
        logger.error(msg);
    }

    public void errorLog(String msg, Exception e) {
        logger.error(msg, e);
    }
}

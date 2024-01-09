package org.example.logging;

import org.slf4j.LoggerFactory;

public class Logger {
    private static final org.slf4j.Logger loggerObj = LoggerFactory.getLogger(Logger.class);

    public void userPrompt(String msg) { loggerObj.info(msg);}
    public void debugLog(String msg) {
        loggerObj.debug(msg);
    }

    public void debugLog(String msg, Exception e) {
        loggerObj.debug(msg,e);
    }

    public void errorLog(String msg) {
        loggerObj.error(msg);
    }

    public void errorLog(String msg,Exception e) {
        loggerObj.error(msg,e);
    }
}

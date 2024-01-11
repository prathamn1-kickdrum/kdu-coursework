package org.example;

import org.example.customcomparator.PubDateAscComparator;
import org.example.customcomparator.PubDateDescComparator;

public class Main {

    public static void main(String[] args) {
        Logger loggerObj = Logger.getLoggerObject();
        loggerObj.debugLog("Test Case 1: without comparator");
        loggerObj.debugLog(TreeSetDemo.treeSetDemo(null).toString());

        loggerObj.debugLog("Test Case 2: with PubDateAscComparator");
        loggerObj.debugLog(TreeSetDemo.treeSetDemo(new PubDateAscComparator()).toString());

        loggerObj.debugLog("Test Case 3: with PubDateDescComparator");
        loggerObj.debugLog(TreeSetDemo.treeSetDemo(new PubDateDescComparator()).toString());
    }
}
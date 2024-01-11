package org.example;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        Logger loggerObj = Logger.getLoggerObject();
        ArrayUtils utils = new ArrayUtils();
        Integer[] intArray = {1, 2, 3, 4, 5};
        loggerObj.debugLog("Original Integer array: " + Arrays.toString(intArray));
        utils.exchangeElements(intArray, 1, 3);
        loggerObj.debugLog("Array after exchanging elements: " + Arrays.toString(intArray));

        // Example with String array
        String[] strArray = {"apple", "banana", "cherry", "date", "elderberry"};
        loggerObj.debugLog("Original String array: " + Arrays.toString(strArray));
        utils.exchangeElements(strArray, 0, 4);
        loggerObj.debugLog("Array after exchanging elements: " + Arrays.toString(strArray));
    }
}
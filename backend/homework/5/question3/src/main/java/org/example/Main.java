package org.example;

public class Main {
    public static void main(String[] args) {

        final Logger loggerObj = Logger.getLoggerObject();
        UserInputHandler inputHandler = new UserInputHandler();

        int inputNumber = inputHandler.readNumber();

        Factorial factorialCalculator = new Factorial(inputNumber);
        Factor factorsCalculator = new Factor(inputNumber);

        Thread factorialThread = new Thread(factorialCalculator);
        Thread factorsThread = new Thread(factorsCalculator);

        factorialThread.start();
        factorsThread.start();

        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            loggerObj.errorLog(e.getMessage());
        }

        loggerObj.debugLog("Main thread finished last.");
    }
}
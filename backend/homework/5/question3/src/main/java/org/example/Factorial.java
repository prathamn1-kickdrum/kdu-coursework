package org.example;

class Factorial implements Runnable {
    private final Logger loggerObj = Logger.getLoggerObject();
    private final int number;
    private long result;

    public Factorial(int number) {
        this.number = number;
        this.result = 1;
    }

    public long getResult() {
        return result;
    }

    @Override
    public void run() {
        calculateFactorial();
    }

    private void calculateFactorial() {
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        loggerObj.debugLog("Factorial of " + number + ": " + result);
    }
}
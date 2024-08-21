package org.example;

class Factor implements Runnable {
    private final int number;
    private final Logger loggerObj = Logger.getLoggerObject();

    public Factor(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        calculateFactors();
    }

    private void calculateFactors() {
        loggerObj.debugLog("Factors of " + number + ": ");
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                loggerObj.debugLog(i + " ");
            }
        }
    }
}

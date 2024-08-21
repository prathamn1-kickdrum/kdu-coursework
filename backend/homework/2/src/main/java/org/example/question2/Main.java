package org.example.question2;

import org.example.logging.Logger;

import java.util.Scanner;

/**
 * The `Main` class provides the main entry point for sentiment analysis on user reviews.
 */
public class Main {

    /**
     * Prints the sentiment analysis results for the provided review.
     */
    public static void printReviewOnFeatureSet(int[] reviewList, Logger logger) {
        for (int review : reviewList) {
            logger.debugLog(String.valueOf(review));
        }
    }

    /**
     * The main method for collecting user input and displaying sentiment analysis results.
     */
    public static void main(String[] args) {
        Logger logger = new Logger();
        Scanner reader = new Scanner(System.in);

        // Prompt user for a review input
        logger.userPrompt("Enter your review: ");
        String review = reader.nextLine();

        // Perform sentiment analysis and print the results
        int[] reviewList = SentimentAnalyzer.detectProsAndCons(review);
        printReviewOnFeatureSet(reviewList, logger);
    }
}

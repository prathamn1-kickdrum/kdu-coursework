package org.example.question2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The `SentimentAnalyzer` class provides methods for sentiment analysis of reviews.
 */
public class SentimentAnalyzer {

    private SentimentAnalyzer() {

    }
    private static final String[][] featureSet = {
            { "ambiance", "ambience", "atmosphere", "decor" },
            { "dessert", "ice cream", "desert" },
            { "food" },
            { "soup" },
            { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
    private static final String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing",
            "awesome", "delicious" };
    private static final String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

    /**
     * Removes trailing special characters from a word.
     */
    private static String removeTrailingSpecialChar(String word) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]+$");

        Matcher matcher = pattern.matcher(word);

        if (matcher.find()) {
            int endIndex = matcher.start();
            return word.substring(0, endIndex);
        } else {
            return word;
        }
    }

    /**
     * Retrieves a list of words from a review, removing trailing special characters.
     */
    private static ArrayList<String> getReviewWordList(String review) {
        ArrayList<String>reviewList = new ArrayList<>();
        for(String str : review.split(" ")) {
            reviewList.add(removeTrailingSpecialChar(str).toLowerCase());
        }
        return reviewList;
    }

    /**
     * Builds a map of words and their positions in a review.
     */
    private static Map<String,ArrayList<Integer>> getReviewMap(ArrayList<String> reviewWordList) {
        Map<String,ArrayList<Integer>> reviewWordMap = new HashMap<>();
        for(int i=0;i<reviewWordList.size();i++) {
            if(!reviewWordMap.containsKey(reviewWordList.get(i))) {
                reviewWordMap.put(reviewWordList.get(i),new ArrayList<>());
            }
            reviewWordMap.get(reviewWordList.get(i)).add(i);
        }
        return reviewWordMap;
    }

    /**
     * Retrieves a HashSet of opinion words.
     */
    private static Set<String> getOpinionWordSet(String[] opinionWords) {
        return new HashSet<>(Arrays.asList(opinionWords));
    }

    /**
     * Determines the sentiment for a specific feature in a review.
     */
    private static int getOpinionOnFeature(String feature, ArrayList<String> reviewWordList,
                                           Map<String, ArrayList<Integer>> reviewWordMap,
                                           Set<String> posOpinionWordSet,
                                           Set<String> negOpinionWordSet) {
        if(reviewWordMap.containsKey(feature)) {
            for(int ind : reviewWordMap.get(feature)) {
                int forWasPatternValue = checkForWasPhrasePattern(ind,reviewWordList,posOpinionWordSet,negOpinionWordSet);
                int opinionFirstPatterValue = checkForOpinionFirstPattern(ind,reviewWordList,posOpinionWordSet,negOpinionWordSet);
                if(forWasPatternValue!=0) return forWasPatternValue;
                else return opinionFirstPatterValue;
            }
        }
        return 0;
    }

    /**
     * Checks for the "was" phrase pattern in the review.
     */
    private static int checkForWasPhrasePattern(int featureIndex, ArrayList<String> reviewWordList,
                                                Set<String> posOpinionWordSet,
                                                Set<String> negOpinionWordSet) {
        if(featureIndex+2<reviewWordList.size() && reviewWordList.get(featureIndex+1).equals("was")) {
            if(posOpinionWordSet.contains(reviewWordList.get(featureIndex+2))) return 1;
            else if(negOpinionWordSet.contains(reviewWordList.get(featureIndex+2))) return -1;
        }
        return 0;
    }

    /**
     * Checks for the opinion-first pattern in the review.
     */
    private static int checkForOpinionFirstPattern(int featureIndex, ArrayList<String> reviewWordList,
                                                   Set<String> posOpinionWordSet,
                                                   Set<String> negOpinionWordSet) {

        if(featureIndex-1>=0) {
            if(posOpinionWordSet.contains(reviewWordList.get(featureIndex-1))) return 1;
            else if(negOpinionWordSet.contains(reviewWordList.get(featureIndex-1))) return -1;
        }
        return 0;
    }

    /**
     * Detects pros and cons based on sentiment analysis of a review.
     */
    public static int[] detectProsAndCons(String review) {
        ArrayList<String> reviewWordList = getReviewWordList(review);
        Map<String,ArrayList<Integer>> reviewWordMap = getReviewMap(reviewWordList);
        Set<String> posOpinionWordSet = getOpinionWordSet(posOpinionWords);
        Set<String> negOpinionWordSet = getOpinionWordSet(negOpinionWords);
        int[] featureReviews = new int[featureSet.length];
        for(int i=0;i<featureSet.length;i++) {
            for(int j=0;j<featureSet[i].length;j++) {
                int reviewOp = getOpinionOnFeature(featureSet[i][j],reviewWordList,reviewWordMap,posOpinionWordSet,negOpinionWordSet);
                if(reviewOp!=0) {
                    featureReviews[i]=reviewOp;
                    break;
                }
            }
        }
        return featureReviews;
    }
}

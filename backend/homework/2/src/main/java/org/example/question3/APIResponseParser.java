package org.example.question3;

/**
 * The `APIResponseParser` class provides methods to parse an API response
 * and extract information related to a book, creating a Book instance.
 */
public class APIResponseParser {

    private APIResponseParser() {

    }

    /**
     * Parses the input text between specified start and end rules and returns the extracted data.
     */
    public static String parse(String response, String startRule, String endRule) {
        int startInd = response.indexOf(startRule);
        int endInd = response.indexOf(endRule, startInd + startRule.length());
        if (startInd != -1 && endInd != -1) {
            return response.substring(startInd + startRule.length(), endInd);
        }
        return null;
    }

    /**
     * Formats the provided ratings count by removing commas and converting it to an integer.
     */
    private static int formatRatingCount(String ratingsCount) {
        if (ratingsCount != null) {
            String modifiedRatingsCount = ratingsCount.replace(",", "");
            return modifiedRatingsCount.isEmpty() ? 0 : Integer.parseInt(modifiedRatingsCount);
        }
        return 0;
    }

    /**
     * Parses the API response and constructs a Book instance with extracted information.
     */
    public static Book parse(String response) {
        Book book = new Book();

        String title = parse(response, "<title>", "<");
        String author = parse(response, "<name>", "<");
        String pubYear = parse(response, "<original_publication_year type=\"integer\">", "<");
        String avgRating = parse(response, "<average_rating>", "<");
        String ratingsCount = parse(response, "<ratings_count type=\"integer\">", "<");
        String imageUrl = parse(response, "<image_url>", "<");

        book.setImageUrl(imageUrl);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(pubYear != null ? Integer.parseInt(pubYear) : 0);
        book.setAverageRating(avgRating != null ? Double.parseDouble(avgRating) : 0.0);
        book.setRatingsCount(formatRatingCount(ratingsCount));

        return book;
    }
}

package org.example;

public class ArrayUtils {

    /**
     * Generic function to swap elements
     */
    public <T> void exchangeElements(T[] array, int index1, int index2) {
        if (index1 < 0 || index1 >= array.length || index2 < 0 || index2 >= array.length) {
            throw new IllegalArgumentException("Invalid exchange indices provided");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

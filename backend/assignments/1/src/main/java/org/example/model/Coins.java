package org.example.model;

public class Coins {
    private String name;
    private String symbol;
    private int rank;
    private long volume;
    private double price;

    // Constructor
    public Coins(int rank, String name, String symbol,double price, long volume) {
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.volume = volume;
        this.price = price;
    }

    public Coins() {

    }

    // Getters
    public String getCoinName() {
        return name;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public int getCoinRank() {
        return rank;
    }

    public synchronized long getCoinVolume() {
        return volume;
    }

    public synchronized double getCoinPrice() {
        return price;
    }

    // Setters
    public void setCoinName(String name) {
        this.name = name;
    }

    public void setCoinSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCoinRank(int rank) {
        this.rank = rank;
    }

    public synchronized void updateCoinVolume(long diff) {
        this.volume +=diff;
    }

    public synchronized void setCoinPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", volume=" + volume +
                ", price=" + price ;
    }


}


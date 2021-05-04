package com.ccm.pokemon.pokemon.domain.valueObjects;

public class FavouriteCounter {

    public FavouriteCounter(int favouriteCounter) {
        this.favouriteCounter = favouriteCounter;
    }

    public int getFavouriteCounter() {
        return favouriteCounter;
    }

    private int favouriteCounter;

    public void addFavouriteCounter() {
        this.favouriteCounter += 1;
    }
}

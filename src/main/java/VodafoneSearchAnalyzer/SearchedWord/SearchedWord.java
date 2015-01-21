package VodafoneSearchAnalyzer.SearchedWord;

import VodafoneSearchAnalyzer.Category;
import VodafoneSearchAnalyzer.SeekingLocation;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class SearchedWord {

    private String word;
    private int countOfSearching;
    private Category searchedIn;
    private SeekingLocation location;

    public SearchedWord(String word, int countOfSearching, Category searchedIn, SeekingLocation location) {
        this.word = word;
        this.countOfSearching = countOfSearching;
        this.searchedIn = searchedIn;
        this.location = location;
    }

    public String getWord() {
        return word;
    }

    public int getCountOfSearching() {
        return countOfSearching;
    }

    public Category getSearchedIn() {
        return searchedIn;
    }

    public SeekingLocation getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "SearchedWord{" +
                "word='" + word + '\'' +
                ", countOfSearching=" + countOfSearching +
                ", searchedIn=" + searchedIn +
                ", location=" + location +
                '}';
    }
}

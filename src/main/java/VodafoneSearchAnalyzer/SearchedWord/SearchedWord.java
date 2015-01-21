package VodafoneSearchAnalyzer.SearchedWord;

import VodafoneSearchAnalyzer.Category;
import VodafoneSearchAnalyzer.SeekingLocation;

import java.io.Serializable;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class SearchedWord implements Comparable<SearchedWord>, Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchedWord)) return false;

        SearchedWord that = (SearchedWord) o;

        if (countOfSearching != that.countOfSearching) return false;
        if (location != that.location) return false;
        if (searchedIn != that.searchedIn) return false;
        if (!word.equals(that.word)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + countOfSearching;
        result = 31 * result + (searchedIn != null ? searchedIn.hashCode() : 0);
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public int compareTo(SearchedWord another) {
        if (!this.getWord().equals(another.getWord())) {
            return this.getWord().compareTo(another.getWord());
        } else {
            return this.getLocation().compareTo(another.getLocation());
        }
    }
}

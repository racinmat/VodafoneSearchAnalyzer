package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
abstract public class AbstractSearchResult implements Comparable<AbstractSearchResult> {

    protected String url;
    protected SearchedWord searchedWord;
    protected SeekingLocation location;

    public AbstractSearchResult(String url, SearchedWord searchedWord, SeekingLocation location) {
        this.url = url;
        this.searchedWord = searchedWord;
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public SearchedWord getSearchedWord() {
        return searchedWord;
    }

    public SeekingLocation getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "VodafoneSearchAnalyzer.AbstractSearchResult{" +
                "url='" + url + '\'' +
                ", searchedWord='" + searchedWord + '\'' +
                ", seekingLocation=" + location +
                '}';
    }

    @Override
    public int compareTo(AbstractSearchResult another) {
        return another.getSearchedWord().compareTo(another.getSearchedWord());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSearchResult)) return false;

        AbstractSearchResult that = (AbstractSearchResult) o;

        if (location != that.location) return false;
        if (!searchedWord.equals(that.searchedWord)) return false;
        if (!url.equals(that.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + searchedWord.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    abstract public String getMetatagsForOutput();

}

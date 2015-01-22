package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;

import java.io.Serializable;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
abstract public class NotLazySearchResult extends AbstractSearchResult implements Serializable {

    public NotLazySearchResult(String url, SearchedWord searchedWord, SeekingLocation location) {
        super(url, searchedWord, location);
    }

}

package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;

import java.io.Serializable;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class LazySearchResult extends AbstractSearchResult implements Serializable {

    public LazySearchResult(String url, SearchedWord searchedWord, SeekingLocation location) {
        super(url, searchedWord, location);
    }

    @Override
    public String getMetatagsForOutput() {
        return "Pro tuto stránku zatím informace nebyly načteny";
    }
}

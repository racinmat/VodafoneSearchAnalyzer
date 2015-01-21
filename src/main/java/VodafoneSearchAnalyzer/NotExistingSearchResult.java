package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class NotExistingSearchResult extends AbstractSearchResult {

    private String errorMessage;

    public NotExistingSearchResult(String url, SearchedWord word, SeekingLocation location) {
        super(url, word, location);
        this.errorMessage = "404 error, stránka neexistuje.";
    }

    @Override
    public String toString() {
        return "VodafoneSearchAnalyzer.NotExistingSearchResult{" +
                "errorMessage='" + errorMessage + '\'' +
                "} " + super.toString();
    }

    @Override
    public String getMetatagsForOutput() {
        return "stránka neexistuje";
    }
}

package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class PdfSearchResult extends AbstractSearchResult {

    public PdfSearchResult(String url, SearchedWord searchedWord, SeekingLocation location) {
        super(url, searchedWord, location);
    }

    @Override
    public String getMetatagsForOutput() {
        return "Toto je pdf.";
    }

}

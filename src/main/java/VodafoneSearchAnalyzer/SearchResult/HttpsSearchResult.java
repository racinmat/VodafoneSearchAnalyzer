package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;

/**
 * Created by Azathoth on 22. 1. 2015.
 */
public class HttpsSearchResult extends NotLazySearchResult {

    public HttpsSearchResult(String url, SearchedWord searchedWord, SeekingLocation location) {
        super(url, searchedWord, location);
    }

    @Override
    public String getMetatagsForOutput() {
        return "Tato stránka je na https, připojení se tedy nezdařilo a html tagy je třeba zjistit ručně.";//TODO: somehow solve connection to https
    }
}

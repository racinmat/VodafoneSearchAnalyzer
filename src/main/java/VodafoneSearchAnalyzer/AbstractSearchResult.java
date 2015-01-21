package VodafoneSearchAnalyzer;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class AbstractSearchResult {

    protected String url;
    protected String searchedWord;
    protected SeekingLocation location;

    public AbstractSearchResult(String url, String searchedWord, SeekingLocation location) {
        this.url = url;
        this.searchedWord = searchedWord;
        this.location = location;
    }

    @Override
    public String toString() {
        return "VodafoneSearchAnalyzer.AbstractSearchResult{" +
                "url='" + url + '\'' +
                ", searchedWord='" + searchedWord + '\'' +
                ", seekingLocation=" + location +
                '}';
    }
}

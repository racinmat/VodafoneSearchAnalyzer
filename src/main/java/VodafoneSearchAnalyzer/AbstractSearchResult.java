package VodafoneSearchAnalyzer;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class AbstractSearchResult {

    protected String url;
    protected String searchedWord;
    protected Category category;

    public AbstractSearchResult(String url, String searchedWord, Category category) {
        this.url = url;
        this.searchedWord = searchedWord;
        this.category = category;
    }

    @Override
    public String toString() {
        return "VodafoneSearchAnalyzer.AbstractSearchResult{" +
                "url='" + url + '\'' +
                ", searchedWord='" + searchedWord + '\'' +
                ", seekingLocation=" + category +
                '}';
    }
}

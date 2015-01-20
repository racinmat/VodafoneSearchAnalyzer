/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class AbstractSearchResult {

    protected String url;
    protected String searchedWord;
    protected String category;

    public AbstractSearchResult(String url, String searchedWord, String category) {
        this.url = url;
        this.searchedWord = searchedWord;
        this.category = category;
    }

    @Override
    public String toString() {
        return "AbstractSearchResult{" +
                "url='" + url + '\'' +
                ", searchedWord='" + searchedWord + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}

package VodafoneSearchAnalyzer;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class SearchedWord {

    private String word;
    private int countOfSearching;
    private Category searchedIn;

    public SearchedWord(String word, int countOfSearching, Category searchedIn) {
        this.word = word;
        this.countOfSearching = countOfSearching;
        this.searchedIn = searchedIn;
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
}

package VodafoneSearchAnalyzer;

import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class ResultForOneWord {

    private String word;
    private List<SearchResult> results;

    public ResultForOneWord(String word, List<SearchResult> results) {
        this.word = word;
        this.results = results;
    }

}

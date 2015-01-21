package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult.LazySearchResult;
import VodafoneSearchAnalyzer.SearchResult.NotLazySearchResult;
import VodafoneSearchAnalyzer.SearchResult.SearchResultFactory;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.Seekers.VodafoneAbstractSeeker;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class OverAllSeeker {

    private List<VodafoneAbstractSeeker> seekers;

    public OverAllSeeker(List<VodafoneAbstractSeeker> seekers) {
        this.seekers = seekers;
    }

    public OverAllSeeker(VodafoneAbstractSeeker... seekers) {
        this.seekers = new ArrayList<VodafoneAbstractSeeker>();
        for (int i = 0; i < seekers.length; i++) {
            this.seekers.add(seekers[i]);
        }
    }

    public List<AbstractSearchResult> searchForWords(List<SearchedWord> words, int results, boolean lazy) throws IOException {
        List<AbstractSearchResult> resultWebsites = new ArrayList<AbstractSearchResult>();
        for (SearchedWord word : words)
            for (VodafoneAbstractSeeker seeker : seekers) {
                System.out.println("Seeking word "+word+" by seeker "+seeker.getClass()+".");
                List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, results, lazy);
                resultWebsites.addAll(resultsForOneWord);
            }
        return resultWebsites;
    }

    public List<AbstractSearchResult> searchForWords(List<SearchedWord> words, boolean lazy) throws IOException {
        List<AbstractSearchResult> resultWebsites = new ArrayList<>();
        int remaining = words.size();
        for (SearchedWord word : words) {
            for (VodafoneAbstractSeeker seeker : seekers) {
                System.out.println("Seeking word "+word+" by seeker "+seeker.getClass()+".");
                List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, seeker.getResults(), lazy);
                resultWebsites.addAll(resultsForOneWord);
            }
            remaining--;
            System.out.println(remaining);
        }
        return resultWebsites;
    }

    public List<AbstractSearchResult> lazyLoadResults(List<LazySearchResult> lazyResults) throws IOException {
        List<AbstractSearchResult> results = new ArrayList<>();
        for (LazySearchResult lazyResult : lazyResults) {
            results.add(SearchResultFactory.createSearchResult(lazyResult));
        }
        return results;
    }

}

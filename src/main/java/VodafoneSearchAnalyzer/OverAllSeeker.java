package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.Seekers.VodafoneAbstractSeeker;

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

    public List<AbstractSearchResult> searchForWords(List<SearchedWord> words, int results) throws IOException {
        List<AbstractSearchResult> resultWebsites = new ArrayList<AbstractSearchResult>();
        for (SearchedWord word : words)
            for (VodafoneAbstractSeeker seeker : seekers) {
                System.out.println("Seeking word "+word+" by seeker "+seeker.getClass()+".");
                List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, results);
                resultWebsites.addAll(resultsForOneWord);
            }
        return resultWebsites;
    }

    public List<AbstractSearchResult> searchForWords(List<SearchedWord> words) throws IOException {
        List<AbstractSearchResult> resultWebsites = new ArrayList<AbstractSearchResult>();
        int remaining = words.size();
        for (SearchedWord word : words) {
            for (VodafoneAbstractSeeker seeker : seekers) {
                System.out.println("Seeking word "+word+" by seeker "+seeker.getClass()+".");
                List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, seeker.getResults());
                resultWebsites.addAll(resultsForOneWord);
            }
            remaining--;
            System.out.println(remaining);
        }
        return resultWebsites;
    }

}

package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.*;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.Seekers.VodafoneAbstractSeeker;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        Collections.addAll(this.seekers, seekers);
    }

    public List<AbstractSearchResult> searchForWords(List<SearchedWord> words, boolean lazy) throws IOException, InvalidArgumentException {
        List<AbstractSearchResult> resultWebsites = new ArrayList<>();
        for (SearchedWord word : words) {
            int remaining = words.size();
            VodafoneAbstractSeeker seeker = chooseSeekerByWord(word);
            System.out.println("Seeking word "+word.toString()+" by seeker "+seeker.getClass()+".");
            List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, seeker.getResults(), lazy);
            resultWebsites.addAll(resultsForOneWord);
            remaining--;
            System.out.println(remaining);
        }
        return resultWebsites;
    }

    public List<AbstractSearchResult> lazyLoadResults(List<LazySearchResult> lazyResults) throws IOException {
        List<AbstractSearchResult> results = new ArrayList<>();
        int count = lazyResults.size();
        for (LazySearchResult lazyResult : lazyResults) {
            results.add(SearchResultFactory.createSearchResult(lazyResult));
            count--;
            System.out.println(count);
        }
        return results;
    }

    public List<NotLazySearchResult> tryToReadHttpsResults(List<NotLazySearchResult> results) throws IOException {
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) instanceof HttpsSearchResult) {
                results.set(i, SearchResultFactory.createAgainFromExistingResult(results.get(i)));
                System.out.println(i);
            }
        }
        return results;
    }

    public VodafoneAbstractSeeker chooseSeekerByWord(SearchedWord word) throws InvalidArgumentException {
        Class seekerClass = word.getLocation().getSeekerClass();
        for (VodafoneAbstractSeeker seeker : seekers) {
            if (seeker.getClass().equals(seekerClass)) {
                return seeker;
            }
        }
        String[] arguments = {"No seeker is set for this word", word.toString()};
        throw new InvalidArgumentException(arguments);
    }
}

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

    public List<SearchResultsCollection> searchForWords(List<SearchedWord> words, boolean lazy) throws IOException, InvalidArgumentException {
        List<SearchResultsCollection> resultWebsites = new ArrayList<>();
        int remaining = words.size();
        for (SearchedWord word : words) {
            VodafoneAbstractSeeker seeker = chooseSeekerByWord(word);
            SearchResultsCollection results = new SearchResultsCollection(word, seeker.getResultsCount());
            System.out.println("Seeking word "+word.toString()+" by seeker "+seeker.getClass()+".");
            List<AbstractSearchResult> resultsForOneWord = seeker.searchForWord(word, seeker.getResultsCount(), lazy);
            results.add(resultsForOneWord);
            remaining--;
            System.out.println(remaining);
            resultWebsites.add(results);
        }
        return resultWebsites;
    }

    public List<SearchResultsCollection> lazyLoadResults(List<SearchResultsCollection> wordResults, boolean isSearchResultCollection) throws IOException, InvalidArgumentException {
        for (SearchResultsCollection wordResult : wordResults) {
            List<AbstractSearchResult> lazyResults = wordResult.getAsList();
            wordResult.replaceLazyResultByLoadedResult(lazyLoadResults(lazyResults));
        }
        return wordResults;
    }
    
    public List<AbstractSearchResult> lazyLoadResults(List<AbstractSearchResult> lazyResults) throws IOException {
        List<AbstractSearchResult> results = new ArrayList<>();
        int count = lazyResults.size();
        for (AbstractSearchResult lazyResult : lazyResults) {
            if(lazyResult instanceof LazySearchResult) {
                LazySearchResult temp = (LazySearchResult) lazyResult;
                results.add(SearchResultFactory.createSearchResult(temp));
            }
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

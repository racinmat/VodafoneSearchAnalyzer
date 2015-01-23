package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Azathoth on 23. 1. 2015.
 */
public class SearchResultsCollection implements Iterable, Serializable {

    private SearchedWord searchedWord;
    private Map<Integer, AbstractSearchResult> results;//indexed from 1
    private int limit;

    public SearchResultsCollection(SearchedWord searchedWord, int limit) {
        this.searchedWord = searchedWord;
        this.results = new HashMap<>();
        this.limit = limit;
    }

    public SearchedWord getSearchedWord() {
        return searchedWord;
    }

    public Map<Integer, AbstractSearchResult> getResults() {
        return results;
    }

    public int getLimit() {
        return limit;
    }

    public SearchResultsCollection add(AbstractSearchResult result) {
        if (getResultsCount() >= limit) {
            throw new UnsupportedOperationException("Limit has been reached, current limit is: "+limit);
        }
        int nextResult = getResultsCount()+1;
        results.put(nextResult, result);
        return this;
    }

    public SearchResultsCollection add(List<AbstractSearchResult> results) {
        for (AbstractSearchResult result : results) {
            add(result);
        }
        return this;
    }

    private int getResultsCount() {
        int size = results.size();
        if (!results.containsKey(size) && size > 0) {
            throw new RuntimeException("missing resultsCount");
        } else {
            return size;
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {

        int current = 0;

        @Override
        public boolean hasNext() {
            return getResultsCount() == current;
        }

        @Override
        public AbstractSearchResult next() {
            current++;
            return results.get(current);
        }

        @Override
        public void remove() {
            results.remove(current);
        }
    }

}

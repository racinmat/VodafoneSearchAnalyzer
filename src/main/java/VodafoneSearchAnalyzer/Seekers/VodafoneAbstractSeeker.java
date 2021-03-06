package VodafoneSearchAnalyzer.Seekers;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult.SearchResultFactory;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import VodafoneSearchAnalyzer.Category;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public abstract class VodafoneAbstractSeeker {

    protected String prefix;
    protected String suffix;
    protected SeekingLocation seekingLocation;
    protected int resultsCount;
    private boolean printDebugInfo = false;

//    public abstract List<VodafoneSearchAnalyzer.SearchResult.SearchResult> searchForWord(String word, int resultsCount) throws IOException;

    public VodafoneAbstractSeeker(String prefix, String suffix, SeekingLocation seekingLocation, int results) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.seekingLocation = seekingLocation;
        this.resultsCount = results;
    }

//    public abstract List<String> getResultsCount(String searchQuery, int resultsCount) throws IOException;

    public List<AbstractSearchResult> searchForWord(SearchedWord word, int results, Category category, boolean lazy) throws IOException {
        if (printDebugInfo) {
            System.out.println("Searching for word " + word + ", will read first " + results + " resultsCount.");
        }
        List<String> resultList = getResults(createSearchQuery(word, category), results);
        if (printDebugInfo) {
            System.out.println("searching by query "+createSearchQuery(word, category));
            System.out.println(createSearchQuery(word));
        }
        return createResults(resultList, word, lazy);
    }

    private List<AbstractSearchResult> createResults(List<String> resultList, SearchedWord word, boolean lazy) throws IOException {
        List<AbstractSearchResult> resultObjects = new ArrayList<AbstractSearchResult>();
        for (String result : resultList) {
            resultObjects.add(SearchResultFactory.createSearchResult(result, word, seekingLocation, lazy));
        }
        return resultObjects;
    }

    public List<AbstractSearchResult> searchForWord(SearchedWord word, int results, boolean lazy) throws IOException {
        if (printDebugInfo) {
            System.out.println("Searching for word " + word + ", will read first " + results + " resultsCount.");
        }
        List<String> resultList = getResults(createSearchQuery(word), results);
        if (printDebugInfo) {
            System.out.println(createSearchQuery(word));
        }
        return createResults(resultList, word, lazy);
    }

    public List<String> getResults(String searchQuery, int results) throws IOException {
        List<String> resultList = new ArrayList<String>();
        Connection connection = Jsoup.connect(searchQuery);
        connection.timeout(3600000);
        Document document = connection.get();
        Elements links = document.select("div.searchResultItem.noImage>h3>a");
        results = Math.min(links.size(), results);              //checks if number of resultsCount is greater or smaller than number of required resultsCount
        for (int i = 0; i < results; i++) {
            Element searchResult = links.get(i);
            Elements linkAsCollection = searchResult.select("a");
            Element link = linkAsCollection.first();
            String linkString = link.attr("href");
            resultList.add(linkString);
        }
        return resultList;
    }

    public SeekingLocation getSeekingLocation() {
        return seekingLocation;
    }

    public int getResultsCount() {
        if(resultsCount == 0) {
            throw new RuntimeException("resultsCount not set");
        }
        return resultsCount;
    }

    abstract public String createSearchQuery(SearchedWord word, Category category);

    abstract public String createSearchQuery(SearchedWord word);
}

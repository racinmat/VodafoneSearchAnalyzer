import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public abstract class VodafoneAbstractSeeker {

    protected String prefix;
    protected String suffix;
    protected String category;
    protected int results;

//    public abstract List<SearchResult> searchForWord(String word, int results) throws IOException;

    public VodafoneAbstractSeeker(String prefix, String suffix, String category, int results) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.category = category;
        this.results = results;
    }

//    public abstract List<String> getResults(String searchQuery, int results) throws IOException;

    public List<AbstractSearchResult> searchForWord(String word, int results) throws IOException {
        System.out.println("Searching for word "+word+", will read first "+results+" results.");
        List<String> resultList = getResults(prefix+word+suffix, results);
        System.out.println("searching by query "+prefix+word+suffix);
        List<AbstractSearchResult> resultObjects = new ArrayList<AbstractSearchResult>();
        for (String result : resultList) {
            resultObjects.add(SearchResultFactory.createSearchResult(result, word, category));
        }
        return resultObjects;
    }

    public List<String> getResults(String searchQuery, int results) throws IOException {
        List<String> resultList = new ArrayList<String>();
        Connection connection = Jsoup.connect(searchQuery);
        Document document = connection.get();
        Elements links = document.select("div.searchResultItem.noImage>h3>a");
        results = Math.min(links.size(), results);              //checks if number of results is greater or smaller than number of required results
        for (int i = 0; i < results; i++) {
            Element searchResult = links.get(i);
            Elements linkAsCollection = searchResult.select("a");
            Element link = linkAsCollection.first();
            String linkString = link.attr("href");
            resultList.add(linkString);
        }
        return resultList;
    }

    public String getCategory() {
        return category;
    }

    public int getResults() {
        if(results == 0) {
            throw new RuntimeException("results not set");
        }
        return results;
    }
}

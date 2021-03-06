package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResultFactory {

    private static boolean printDebugInfo = false;

    public static AbstractSearchResult createSearchResult(String url, SearchedWord word, SeekingLocation location, boolean lazy) throws IOException {
        return createSearchResult(url, word, location, lazy, false);
    }

    public static NotLazySearchResult createSearchResult(LazySearchResult lazyResult) throws IOException {
        return createNotLazySearchResult(lazyResult.getUrl(), lazyResult.getSearchedWord(), lazyResult.getLocation(), true);
    }

    public static NotLazySearchResult createAgainFromExistingResult(AbstractSearchResult result) throws IOException {
        return createNotLazySearchResult(result.getUrl(), result.getSearchedWord(), result.getLocation(), true);
    }

    private static NotLazySearchResult createNotLazySearchResult(String url, SearchedWord word, SeekingLocation location, boolean readHttps) throws IOException {
        AbstractSearchResult result = createSearchResult(url, word, location, false, readHttps);
        if (result instanceof NotLazySearchResult) {
            return (NotLazySearchResult) result;
        }
        throw new RuntimeException("something is wrong with class of result");
    }

    private static AbstractSearchResult createSearchResult(String url, SearchedWord word, SeekingLocation location, boolean lazy, boolean readHttps) throws IOException {
        if (lazy) {
            return new LazySearchResult(url, word, location);
        }
        if (printDebugInfo) {
            System.out.println("creating resultsCount for "+url);
        }
        if (url.endsWith(".pdf")) {
            return new PdfSearchResult(url, word, location);
        } else if (!readHttps && url.startsWith("https")) {
            return new HttpsSearchResult(url, word, location);
        } else {
            Connection connection = Jsoup.connect(url);
            String descriptionString = "";
            String keywordsString = "";
            try {
                connection.timeout(3600000);
                Document document = connection.get();
                Elements descriptionArray = document.select("meta[name=\"description\"]");
                Element description = descriptionArray.first();
                if (description == null) {
                    descriptionString = "Tag description ve stránce chybí.";
                } else {
                    descriptionString = description.attr("content");
                }
                Elements keywordsArray = document.select("meta[name=\"keywords\"]");
                Element keywords = keywordsArray.first();
                if (keywords == null) {
                    keywordsString = "Tag keywords ve stránce chybí.";
                } else {
                    keywordsString = keywords.attr("content");
                }
                return new SearchResult(url, word, location, keywordsString, descriptionString);
            } catch (HttpStatusException e) {
                return new NotExistingSearchResult(url, word, location);
            } catch (SocketTimeoutException|SSLHandshakeException e) {
                e.printStackTrace();
                System.out.println("Could not connect to website with url: "+url);
                return null;
            }
        }
    }

}

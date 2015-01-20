import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResultFactory {

    public static AbstractSearchResult createSearchResult(String url, String word, String category) throws IOException {
        System.out.println("creating results for "+url);
        Connection connection = Jsoup.connect(url);
        String descriptionString = "";
        String keywordsString = "";
        try {
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
            return new SearchResult(url, word, category, keywordsString, descriptionString);
        } catch (HttpStatusException e) {
            return new NotExistingSearchResult(url, word, category);
        }
    }

}

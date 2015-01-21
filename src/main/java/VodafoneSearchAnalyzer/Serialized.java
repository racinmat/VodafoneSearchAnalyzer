package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult.LazySearchResult;
import VodafoneSearchAnalyzer.SearchResult.NotLazySearchResult;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum Serialized {

    SEARCH_WORDS("searchWords.azathoth", SearchedWord.class),
    SEARCH_RESULTS("searchResults.azathoth", NotLazySearchResult.class),
    SEARCH_RESULTS_WITHOUT_TAGS("searchResultsWithoutTags.azathoth", LazySearchResult.class);

    private String filename;
    private Class objectClass;

    Serialized(String filename, Class objectClass) {
        this.filename = filename;
        this.objectClass = objectClass;
    }

    public String getFilename() {
        return filename;
    }

    public Class getObjectClass() {
        return objectClass;
    }

    public static Serialized createFromList(List list) throws InvalidArgumentException {
        Object element = list.get(0);
        for (Serialized serialized : Serialized.values()) {
            if (serialized.getObjectClass().equals(element.getClass())) {
                return serialized;
            }
        }
        String[] output = {"not known class", list.toString()};
        throw new InvalidArgumentException(output);
    }
}

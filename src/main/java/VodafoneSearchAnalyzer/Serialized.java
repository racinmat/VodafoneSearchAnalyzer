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
            if (isChildOfParentOrSame(serialized.getObjectClass(), element.getClass())) {
                return serialized;
            }
        }
        String[] output = {"not known class", element.getClass().toString(), element.toString()};
        throw new InvalidArgumentException(output);
    }

    private static boolean isChildOfParentOrSame(Class parent, Class<? extends Object> child) {
        if (parent.equals(child)) {
            return true;
        } else {
            Class iter = child;
            while(!iter.equals(Object.class)) {
                iter = iter.getSuperclass();
                if (iter.equals(parent)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.LazySearchResult;
import VodafoneSearchAnalyzer.SearchResult.NotLazySearchResult;
import VodafoneSearchAnalyzer.SearchResult.SearchResultsCollection;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.util.List;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class FileSerializer {

    public static void serialize(List list) throws InvalidArgumentException, IOException {
        Serialized type = Serialized.createFromList(list);
        FileOutputStream fileOut = new FileOutputStream(type.getFilename());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(list);
        out.close();
        fileOut.close();
    }

    public static List<LazySearchResult> deserializeSearchResultsWithoutTags() {
        return(List<LazySearchResult>) loadFromFile(Serialized.SEARCH_RESULTS_WITHOUT_TAGS.getFilename());
    }

    public static List<NotLazySearchResult> deserializeSearchResults() {
        return (List<NotLazySearchResult>) loadFromFile(Serialized.SEARCH_RESULTS.getFilename());
    }

    public static List<SearchedWord> deserializeSearchedWords() {
        return (List<SearchedWord>) loadFromFile(Serialized.SEARCH_WORDS.getFilename());
    }

    public static List<SearchResultsCollection> deserializeSeachredWordCollections() {
        return (List<SearchResultsCollection>) loadFromFile(Serialized.SEARCH_RESULTS_COLLECTION.getFilename());
    }

    private static Object loadFromFile(String filename) {
        Object results;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            results = in.readObject();
            in.close();
            fileIn.close();
            return results;
        } catch(IOException i) {
            i.printStackTrace();
            return null;
        } catch(ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return null;
        }

    }

}

package VodafoneSearchAnalyzer;

import java.util.UnknownFormatConversionException;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class SearchedWordFactory {

    public static SearchedWord create(String word, int countOfSearchings, String categoryReportName) {
        for (Category category : Category.values()) {
            if(category.getReportName().equals(categoryReportName)) {
                return new SearchedWord(word, countOfSearchings, category);
            }
        }
        throw new UnknownFormatConversionException("unknown seekingLocation: "+categoryReportName);
    }
}

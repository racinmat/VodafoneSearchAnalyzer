package VodafoneSearchAnalyzer.SearchedWord;

import VodafoneSearchAnalyzer.Category;
import VodafoneSearchAnalyzer.SeekingLocation;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.String;
import java.text.ParseException;
import java.util.UnknownFormatConversionException;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class SearchedWordFactory {

    public static SearchedWord create(String word, int countOfSearchings, String locationAndCategoryReportName, boolean doNotUseFilters) throws ParseException {
        int delimiter = 0;
        try {
            delimiter = locationAndCategoryReportName.indexOf(":");
            String locationName = locationAndCategoryReportName.substring(0, delimiter);     //everything before : is location name
            String categoryName = locationAndCategoryReportName.substring(delimiter+1, locationAndCategoryReportName.length());     //+1 is here to throw off the delimiter
            if (doNotUseFilters) {
                return new SearchedWord(word, countOfSearchings, Category.OVER_ALL, SeekingLocation.createFromReportName(locationName));
            } else {
                return new SearchedWord(word, countOfSearchings, Category.createFromReportName(categoryName), SeekingLocation.createFromReportName(locationName));
            }
        } catch (InvalidArgumentException|StringIndexOutOfBoundsException e) {
            throw new ParseException("unknown seekingAndCategoryLocation: "+locationAndCategoryReportName+", caused by "+e.toString(), delimiter);
        }
    }
}

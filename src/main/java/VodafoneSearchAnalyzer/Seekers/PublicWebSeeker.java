package VodafoneSearchAnalyzer.Seekers;

import VodafoneSearchAnalyzer.Category;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.SeekingLocation;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class PublicWebSeeker extends VodafoneAbstractSeeker {

    public PublicWebSeeker(int results) {//c=here belongs location number
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", SeekingLocation.PUBLIC_WEB, results);
    }

    public PublicWebSeeker() {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", SeekingLocation.PUBLIC_WEB, 0);
    }

    @Override
    public String createSearchQuery(SearchedWord word, Category category) {
        return prefix+word.getWord()+suffix;//categories are not working yet
    }

    @Override
    public String createSearchQuery(SearchedWord word) {
        return prefix+word.getWord()+suffix;
    }


}

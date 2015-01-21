package VodafoneSearchAnalyzer.Seekers;

import VodafoneSearchAnalyzer.Category;
import VodafoneSearchAnalyzer.SeekingLocation;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class PublicWebSeeker extends VodafoneAbstractSeeker {

    public PublicWebSeeker(int results) {//c=here belongs category number
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", SeekingLocation.PUBLIC_WEB, results);
    }

    public PublicWebSeeker() {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", SeekingLocation.PUBLIC_WEB, 0);
    }

    @Override
    public String createSearchQuery(String word, Category category) {
        return prefix+word+suffix;//categories are not working yet
    }

    @Override
    public String createSearchResult(String word) {
        return prefix+word+suffix;
    }


}

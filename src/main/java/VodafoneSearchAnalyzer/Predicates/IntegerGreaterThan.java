package VodafoneSearchAnalyzer.Predicates;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import com.google.common.base.Predicate;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class IntegerGreaterThan<HavingInteger> implements Predicate<HavingInteger> {

    private int limit;

    public IntegerGreaterThan(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean apply(HavingInteger searchedWord) {
        SearchedWord temp = (SearchedWord) searchedWord;
        return temp.getCountOfSearching() > limit;
    }
}

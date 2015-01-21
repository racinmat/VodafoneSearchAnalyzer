package VodafoneSearchAnalyzer.SearchedWord;

import VodafoneSearchAnalyzer.Predicates.IntegerGreaterThan;
import com.google.common.collect.Collections2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.sql.rowset.Predicate;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchWordsProvider {

    private static String filename = "Report.csv";
    private static int limit = 10;
    private static boolean doNotUseFilters = true;
    private static boolean printDebugInfo = false;

    public static List<SearchedWord> getWordsToBeSearched() throws IOException {
        File wordsFile = new File(filename);
        CSVParser wordsParsed = CSVParser.parse(wordsFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
        List<CSVRecord> rows = wordsParsed.getRecords();
        Collection<SearchedWord> words = new ArrayList<SearchedWord>();
        rows.remove(0);//removes first entry, where are names of columns
//        for (int i = 0; i < words.size(); i++) {
//            if(i > 15000) {
//                words.remove(i);
//            }
//        }
//        System.out.println("CSV rows:");
        for (CSVRecord row : rows) {
            try {
                words.add(SearchedWordFactory.create(row.get(1), Integer.parseInt(row.get(2)), row.get(0), doNotUseFilters));
            } catch (ParseException e) {
                if(row.get(0).startsWith("Public")) {
                    if (printDebugInfo) {
                        System.out.println(row.toString());
                        System.out.println(e.toString());
                    }
                }
            }
        }
        if (printDebugInfo) {
            System.out.println("searched words:");
            System.out.println(words.size());
        }
        words = Collections2.filter(words, new IntegerGreaterThan<SearchedWord>(limit));
        System.out.println(words.size());
//        for (SearchedWord word : words) {
//            System.out.println(word.toString());
//        }
        List<SearchedWord> outputWords = new ArrayList<>();
        for (SearchedWord word : words) {
            outputWords.add(word);
        }
        return outputWords;
    }

}

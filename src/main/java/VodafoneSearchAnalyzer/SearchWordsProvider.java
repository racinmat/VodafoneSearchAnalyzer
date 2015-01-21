package VodafoneSearchAnalyzer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchWordsProvider {

    private static String filename = "Report.csv";
    private static int limit = 10;

    public static List<SearchedWord> getWordsToBeSearched() throws IOException {
//        List<String> words = new ArrayList<String>();
//        words.add("tarif");
//        words.add("telefon");
//        words.add("samoobsluha");
//        words.add("faktura");
        File wordsFile = new File(filename);
        CSVParser wordsParsed = CSVParser.parse(wordsFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
        List<CSVRecord> rows = wordsParsed.getRecords();
        List<SearchedWord> words = new ArrayList<SearchedWord>();
        for (CSVRecord row : rows) {
            words.add(SearchedWordFactory.create(row.get(2), Integer.parseInt(row.get(3)), row.get(1)));
            System.out.println(row.toString());
        }
        return words;
    }

}

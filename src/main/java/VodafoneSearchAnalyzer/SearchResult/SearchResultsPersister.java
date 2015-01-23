package VodafoneSearchAnalyzer.SearchResult;

import VodafoneSearchAnalyzer.SearchResult.ExcelOutput.WriteExcel;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResultsPersister {

    public void persistSearchResults(List<SearchResultsCollection> results) throws IOException, WriteException {
        WriteExcel sheet = new WriteExcel();
        sheet.setOutputFile("Output.xls");
        sheet.write(results);
    }

}

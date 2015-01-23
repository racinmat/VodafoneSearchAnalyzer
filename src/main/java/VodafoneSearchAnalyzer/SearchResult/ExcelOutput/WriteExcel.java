package VodafoneSearchAnalyzer.SearchResult.ExcelOutput;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult.SearchResultsCollection;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public class WriteExcel {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String outputFile;
    private WritableSheet excelSheet;
    private WritableWorkbook workbook;

    public void setOutputFile(String inputFile) {
        this.outputFile = inputFile;
    }


    private void initWriting() throws IOException, WriteException {
        File file = new File(outputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("cs", "CS"));

        workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Sheet1", 0);
        excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);

    }

    public void write(List<SearchResultsCollection> results) throws IOException, WriteException {
        initWriting();
        createContent(excelSheet, results);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "Sekce");
        addCaption(sheet, 1, 0, "Hledaný výraz");
        addCaption(sheet, 2, 0, "Počet vyhledávání");
        addCaption(sheet, 3, 0, "Url výsledku");
        addCaption(sheet, 4, 0, "Meta tagy");
        //column, row
    }

    private void createContent(WritableSheet sheet, List<SearchResultsCollection> results) throws WriteException {
        int row = 1;
        for (SearchResultsCollection resultCollection : results) {
            addLabel(sheet, 0, row, resultCollection.getSearchedWord().getLocation().getReportName());
            addLabel(sheet, 1, row, resultCollection.getSearchedWord().getWord());
            addLabel(sheet, 2, row, Integer.toString(resultCollection.getSearchedWord().getCountOfSearching()));
            for (AbstractSearchResult result : resultCollection) {
                addLabel(sheet, 3, row, result.getUrl());
                addLabel(sheet, 4, row, result.getMetatagsForOutput());
                row++;
            }
            sheet.setRowView(row, 3000);
        }
        sheet.setColumnView(3, 10000);
        sheet.setColumnView(4, 6000);
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row, Integer integer) throws WriteException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

}

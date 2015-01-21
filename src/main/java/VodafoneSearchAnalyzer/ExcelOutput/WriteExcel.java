package VodafoneSearchAnalyzer.ExcelOutput;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import VodafoneSearchAnalyzer.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
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

    public void setOutputFile(String inputFile) {
        this.outputFile = inputFile;
    }

    public void write(List<AbstractSearchResult> results) throws IOException, WriteException {
        File file = new File(outputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("cs", "CS"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Sheet1", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
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

    private void createContent(WritableSheet sheet, List<AbstractSearchResult> results) throws WriteException, RowsExceededException {
        sortResults(results);
        boolean sameWordSearched = false;
        boolean sameSection = false;
        SearchResult previous = null;
        for (int i = 0; i < results.size(); i++) {
            if (!sameSection) {
                addLabel(sheet, 0, i, results.get(i).getLocation().getReportName());
            }
            if (!sameWordSearched) {
                addLabel(sheet, 1, i, results.get(i).getSearchedWord().getWord());
                addLabel(sheet, 2, i, Integer.toString(results.get(i).getSearchedWord().getCountOfSearching()));
            }

            addLabel(sheet, 3, i, results.get(i).getUrl());
            addLabel(sheet, 4, i, results.get(i).getMetatagsForOutput());

            if (i > 0) {
                sameSection = results.get(i-1).getLocation().equals(results.get(i).getLocation());
                sameWordSearched = results.get(i-1).getSearchedWord().equals(results.get(i).getSearchedWord());
            }
        }

        // Write a few number
        for (int i = 1; i < 10; i++) {
            // First column
            addNumber(sheet, 0, i, i + 10);
            // Second column
            addNumber(sheet, 1, i, i * i);
        }
        // Lets calculate the sum of it
        StringBuffer buf = new StringBuffer();
        buf.append("SUM(A2:A10)");
        Formula f = new Formula(0, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(B2:B10)");
        f = new Formula(1, 10, buf.toString());
        sheet.addCell(f);

        // now a bit of text
        for (int i = 12; i < 20; i++) {
            // First column
            addLabel(sheet, 0, i, "Boring text " + i);
            // Second column
            addLabel(sheet, 1, i, "Another text");
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s) throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row, Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    private void sortResults(List<AbstractSearchResult> results) {
        Collections.sort(results);
    }


}

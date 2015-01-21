package VodafoneSearchAnalyzer.SearchResult.ExcelOutput;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import VodafoneSearchAnalyzer.SearchResult.AbstractSearchResult;
import VodafoneSearchAnalyzer.SearchResult.SearchResult;
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
        for (int i = 0; i < results.size(); i++) {
            int row = i+1;
            if (i > 0) {
                sameSection = results.get(i-1).getLocation().equals(results.get(i).getLocation());
                sameWordSearched = results.get(i-1).getSearchedWord().equals(results.get(i).getSearchedWord());
            }
            if (!sameSection) {
                addLabel(sheet, 0, row, results.get(i).getLocation().getReportName());
            }
            if (!sameWordSearched) {
                addLabel(sheet, 1, row, results.get(i).getSearchedWord().getWord());
                addLabel(sheet, 2, row, Integer.toString(results.get(i).getSearchedWord().getCountOfSearching()));
            }

            addLabel(sheet, 3, row, results.get(i).getUrl());
            addLabel(sheet, 4, row, results.get(i).getMetatagsForOutput());

            sheet.setRowView(row, 3000);
        }
        sheet.setColumnView(3, 10000);
        sheet.setColumnView(4, 6000);

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

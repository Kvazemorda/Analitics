package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.controller.SourceVisitedController;
import com.analytics.entity.report.SourceVisited;
import com.analytics.excel.StyleExcel;
import com.analytics.excel.CreateExcelReport;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelSourceVisited implements FillingExcel {
    private ArrayList<SourceVisited> sourceVisitedList;
    public static final int SOURCE_CONVERSATION = 1;
    public static final int QUALITY_PERC = 0;
    public static final String SOURCE_COLUMN = "B";
    public static final String QUALITY_COLUMN = "A";
    public static final String SOURCE_CONVERS_NR = "source";
    public static final String QUALITY_PERC_NR = "quality";
    public static final String MORE_CONVERSATION_RN = "moreConversation";
    public static final String MORE_VISITED_RN = "moreVisited";
    private ExcelRecommendation excelRecommendation;

    public ExcelSourceVisited(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        //get list source visited site
        this.sourceVisitedList = new SourceVisitedController().getVisitedSources(queryClient);
        this.excelRecommendation = excelRecommendation;
        fillListToExcel(CreateExcelReport.sheetData);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        for(int i = 0; i < sourceVisitedList.size(); i++){
            Row row = sheet.getRow(i+1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell sourceCell = row.createCell(SOURCE_CONVERSATION);
            String conversation = decimalFormat.format(sourceVisitedList.get(i).getConversation_per());
            sourceCell.setCellValue(sourceVisitedList.get(i).getSource()
            + " (" + conversation + "%)");

            Cell qualityCell = row.createCell(QUALITY_PERC);
            qualityCell.setCellValue(sourceVisitedList.get(i).getQuality());
        }
        //change range for Source column
        changeRange(2, sourceVisitedList.size() + 1, SOURCE_COLUMN, SOURCE_CONVERS_NR);
        //change range for Quality column
        changeRange(2, sourceVisitedList.size() + 1, QUALITY_COLUMN, QUALITY_PERC_NR);

        changeCellFromRange(MORE_VISITED_RN, getFirstSource());
        changeCellFromRange(MORE_CONVERSATION_RN, getFirstConversation());
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheetData.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
        rangeCell.setRefersToFormula(reference);

    }

    private String getFirstSource(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < sourceVisitedList.size(); i++){
            if(max < sourceVisitedList.get(i).getQuality()){
                max = sourceVisitedList.get(i).getQuality();
                index = i;
            }
        }
        String firstSource = decimalFormat.format(max);
        return "Болше всего трафика - " + sourceVisitedList.get(index).getSource() + " (" + firstSource + ")";
    }

    private String getFirstConversation(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < sourceVisitedList.size(); i++){
            if(max < sourceVisitedList.get(i).getConversation_per()){
                max = sourceVisitedList.get(i).getConversation_per();
                index = i;
            }
        }
        String firstQuality = decimalFormat.format(max);
        return "Самый высокий % конверсии - " + sourceVisitedList.get(index).getSource() + " (" + firstQuality + "%)";
    }

    @Override
    public void changeCellFromRange(String rangeName, String changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.createRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        c.setCellStyle(StyleExcel.STYLE_DESCRIPTION);
    }
}


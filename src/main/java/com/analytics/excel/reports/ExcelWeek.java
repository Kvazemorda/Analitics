package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.WeekDAO;
import com.analytics.entity.report.Week;
import com.analytics.excel.ConfigExcel;
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

public class ExcelWeek implements FillingExcel {
    public ArrayList<Week> excelWeeks;
    public static final int DAY_WEEK_CELL = 17;
    public static final int DAY_WEEK_VISITED_CELL = 18;
    public static final int DAY_WEEK_CONVERSATION_CELL = 19;
    public static final String DAY_WEEK_RN = "dayWeek";
    public static final String DAY_WEEK_VISITED_RN = "dayWeekVisited";
    public static final String DAY_WEEK_CONVERSATION_RN = "dayWeekConversation";
    public static final String DAY_WEEK_COLUMN = "R";
    public static final String DAY_WEEK_VISITED_COLUMN = "S";
    public static final String DAY_WEEK_CONVERSATION_COLUMN = "T";
    public static final String POPULAR_DAY_OF_WEEK = "popularDayOfWeek";
    public static final String MORE_CONVERSATION_DAY_OF_WEEK = "moreConversionDayOfWeek";
    private ExcelRecommendation excelRecommendation;

    private DecimalFormat decimalFormat;

    public ExcelWeek(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        this.excelRecommendation = excelRecommendation;
        decimalFormat = new DecimalFormat("##0.00");
        this.excelWeeks = new WeekDAO().getWeekList(queryClient);
        fillListToExcel(CreateExcelReport.sheet);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        double maxVisited = 0.0;
        String dayOfWeekVisited = "";
        double maxConversation = 0.0;
        String dayOfWeekConversation = "";
        for(int i = 0; i < excelWeeks.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellDay = row.createCell(DAY_WEEK_CELL);
            cellDay.setCellValue(excelWeeks.get(i).getDayWeek());

            Cell cellVisited = row.createCell(DAY_WEEK_VISITED_CELL);
            cellVisited.setCellValue(excelWeeks.get(i).getDayWeekVisited());

            Cell cellConversation = row.createCell(DAY_WEEK_CONVERSATION_CELL);
            cellConversation.setCellValue(excelWeeks.get(i).getDayWeekConversation());
            if(maxVisited < excelWeeks.get(i).getDayWeekVisited()){
                maxVisited = excelWeeks.get(i).getDayWeekVisited();
                dayOfWeekVisited = excelWeeks.get(i).getDayWeek();
            }
            if(maxConversation < excelWeeks.get(i).getDayWeekConversation() / excelWeeks.get(i).getDayWeekVisited()){
                maxConversation = excelWeeks.get(i).getDayWeekConversation() / excelWeeks.get(i).getDayWeekVisited();
                dayOfWeekConversation = excelWeeks.get(i).getDayWeek();
            }
        }
        changeRange(2, excelWeeks.size() + 1, DAY_WEEK_COLUMN, DAY_WEEK_RN);
        changeRange(2, excelWeeks.size() + 1, DAY_WEEK_VISITED_COLUMN, DAY_WEEK_VISITED_RN);
        changeRange(2, excelWeeks.size() + 1, DAY_WEEK_CONVERSATION_COLUMN, DAY_WEEK_CONVERSATION_RN);

        if(dayOfWeekConversation.equals("")){
            changeCellFromRange(MORE_CONVERSATION_DAY_OF_WEEK, "Нет конверсии");
        }else {
            changeCellFromRange(MORE_CONVERSATION_DAY_OF_WEEK, dayOfWeekConversation + " - самый высокий коэф. конверсии");
        }

        if(dayOfWeekVisited.equals("")){
            changeCellFromRange(POPULAR_DAY_OF_WEEK, "Нет сеансов");
        }else{
            changeCellFromRange(POPULAR_DAY_OF_WEEK, dayOfWeekVisited + " - больше всего сеансов");
        }
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheet.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
        rangeCell.setRefersToFormula(reference);
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
        c.setCellStyle(ConfigExcel.STYLE_DESCRIPTION);
    }
}

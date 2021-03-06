package com.analytics.report.excel.reports;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.dao.DynamicConversationDAO;
import com.analytics.report.entity.report.DynamicConversation;
import com.analytics.report.excel.StyleExcel;
import com.analytics.report.excel.CreateExcelReport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelDynamicConversation implements FillingExcel{
    public ArrayList<DynamicConversation> dynamicConversations;
    public static final int DAY_VISITED_CELL = 6;
    public static final int VISITED_CELL = 8;
    public static final int COUNT_CONVERSATION_CELL = 7;
    public static final String DAY_VISITED_RN = "dayVisited";
    public static final String VISITED_RN = "visitedDiv10";
    public static final String COUNT_CONVERSATION_RN = "countConversation";
    public static final String DAY_VISITED_COLUMN = "G";
    public static final String VISITED_COLUMN = "I";
    public static final String COUNT_CONVERSATION_COLUMN = "H";
    private ExcelRecommendation excelRecommendation;

    private DecimalFormat decimalFormat;

    public ExcelDynamicConversation(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        decimalFormat = new DecimalFormat("##0.00");
        this.dynamicConversations = new DynamicConversationDAO().getList(queryClient);
        this.excelRecommendation = excelRecommendation;
        fillListToExcel(CreateExcelReport.sheetData);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        for(int i = 0; i < dynamicConversations.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellSource = row.createCell(DAY_VISITED_CELL);
            cellSource.setCellValue(dynamicConversations.get(i).getDate());
            cellSource.setCellStyle(getStyle());

            Cell cellQuality = row.createCell(VISITED_CELL);
            cellQuality.setCellValue(dynamicConversations.get(i).getVisited());

            Cell cellConversation = row.createCell(COUNT_CONVERSATION_CELL);
            cellConversation.setCellValue(dynamicConversations.get(i).getCountConversation());

        }
        changeRange(2, dynamicConversations.size() + 1, DAY_VISITED_COLUMN, DAY_VISITED_RN);
        changeRange(2, dynamicConversations.size() + 1, VISITED_COLUMN, VISITED_RN);
        changeRange(2, dynamicConversations.size() + 1, COUNT_CONVERSATION_COLUMN, COUNT_CONVERSATION_RN);

    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheetData.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
        rangeCell.setRefersToFormula(reference);
    }

    @Override
    public void changeCellFromRange(String rangeName, String changeValue) {

    }

    private CellStyle getStyle(){
        CellStyle cellStyle = StyleExcel.STYLE_DATE;
            return cellStyle;
        }
}

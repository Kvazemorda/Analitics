package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.GoalDAO;
import com.analytics.entity.report.Goal;
import com.analytics.excel.CreateExcelReport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelGoal implements FillingExcel {
    public ArrayList<Goal> goalList;
    public static final int GOAL_CELL = 4;
    public static final int GOAL_QUALITY_CELL = 5;
    public static final String GOAL_QUALITY_RN = "goalQuality";
    public static final String GOAL_RN = "goal";
    public static final String GOAL_COLUMN = "E";
    public static final String GOAL_QUALITY_COLUMN = "F";
    private DecimalFormat decimalFormat;
    private ExcelRecommendation excelRecommendation;

    public ExcelGoal(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        decimalFormat = new DecimalFormat("##0.00");
        this.excelRecommendation = excelRecommendation;
        this.goalList = new GoalDAO().getGoalList(queryClient);
        fillListToExcel(CreateExcelReport.sheet);
    }


    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        for(int i = 0; i < goalList.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellSource = row.createCell(GOAL_CELL);
            cellSource.setCellValue(goalList.get(i).getGoal());

            Cell cellQuality = row.createCell(GOAL_QUALITY_CELL);
            cellQuality.setCellValue(goalList.get(i).getQuality());
        }
        changeRange(2, goalList.size() + 1, GOAL_COLUMN, GOAL_RN);
        changeRange(2, goalList.size() + 1, GOAL_QUALITY_COLUMN, GOAL_QUALITY_RN);
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheet.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
        rangeCell.setRefersToFormula(reference);
    }

    @Override
    public void changeCellFromRange(String rangeName, String changeValue) {

    }
}

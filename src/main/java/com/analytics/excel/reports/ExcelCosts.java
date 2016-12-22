package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.CompanyCostsDAO;
import com.analytics.entity.report.CompanyCosts;
import com.analytics.excel.ConfigExcel;
import com.analytics.excel.CreateExcelReport;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelCosts implements FillingExcel {
    public ArrayList<CompanyCosts> costCompany;
    public static final int COMPANY_CELL = 1;
    public static final int AD_CELL = 5;
    public static final int CLICKS_CELL = 8;
    public static final int POSITION_CELL = 10;
    public static final int CONVERSATION_CELL = 11;
    public static final int CONVERSATION_RATE_CELL = 13;
    public static final int COSTS_CELL = 15;
    public static final int CTR_CELL = 17;
    public static final int COST_ONE_CONVERSATION_CELL = 18;
    public static final String COMPANY_RN = "company";
    public static final String KEY_WAS_CLICKED_RN = "keyWasClicked";
    public static final String KEY_WAS_GAVE_COVERSATION = "keyWasGaveConversation";
    public static final String KEY_WAS_GAVE_COVERSATION_RATE = "keyCoversationRate";
    public static final String SEND_ALL_TWO = "sendall2";
    public static final String COST_CONVERSATION = "costConversation";
    public static final String EFFECIENT_CONVERSATION_TWO = "effecientConversation2";
    public static final String KEY_WITHOUT_CONVERSATION = "keyWithoutConversation";
    public static final String COST_WITHOUT_CONVERSATION = "costWithoutConversation";
    private ExcelRecommendation excelRecommendation;
    private QueryClient queryClient;

    private DecimalFormat decimalFormat;

    public ExcelCosts(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        this.excelRecommendation = excelRecommendation;
        this.queryClient = queryClient;
        costCompany = new CompanyCostsDAO().getListCosts(queryClient);
        decimalFormat = new DecimalFormat("##0.00");
        fillListToExcel(CreateExcelReport.sheetReport);

    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(COMPANY_RN);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.createRow(cells[0].getRow());
        int keyWasClicked = 0;
        int keyWasConversation = 0;
        int keyWithoutConversation = 0;
        double sendAll = 0.0;
        double costKeyGiveConversation = 0.0;
        double costWithoutConversation = 0.0;
        double costConversationRate = 0.0;
        for(int i = 0; i < costCompany.size(); i++){
            Row row = sheet.getRow(r.getRowNum() + i);
            if(row == null){
                row = sheet.createRow(r.getRowNum() + i);
            }
            sendAll += costCompany.get(i).getCosts();

            if(costCompany.get(i).getClick() > 0){
               keyWasClicked++;
            }
            if(costCompany.get(i).getConversation() > 0){
                keyWasConversation++;
                costKeyGiveConversation += costCompany.get(i).getCosts();
            }
            if(costCompany.get(i).getConversation() == 0){
                keyWithoutConversation++;
                costWithoutConversation += costCompany.get(i).getCosts();
            }

            int conversation = costCompany.get(i).getConversation();
            Cell cellCompany = row.createCell(COMPANY_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),COMPANY_CELL, AD_CELL - 1));
            cellCompany.setCellValue(costCompany.get(i).getCompany());
            cellCompany.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellAd = row.createCell(AD_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),AD_CELL, CLICKS_CELL - 1));
            cellAd.setCellValue(costCompany.get(i).getAd());
            cellAd.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellClicks = row.createCell(CLICKS_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),CLICKS_CELL, POSITION_CELL - 1));
            cellClicks.setCellValue(costCompany.get(i).getClick());
            cellClicks.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellPosition = row.createCell(POSITION_CELL);
            cellPosition.setCellValue(costCompany.get(i).getPosition());
            cellPosition.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellConversation = row.createCell(CONVERSATION_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),CONVERSATION_CELL, CONVERSATION_RATE_CELL - 1));
            cellConversation.setCellValue(costCompany.get(i).getConversation());
            cellConversation.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellConversationPer = row.createCell(CONVERSATION_RATE_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),CONVERSATION_RATE_CELL, COSTS_CELL - 1));
            cellConversationPer.setCellValue(costCompany.get(i).getConversationRate());
            cellConversationPer.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellCosts = row.createCell(COSTS_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),COSTS_CELL, CTR_CELL - 1));
            cellCosts.setCellValue(costCompany.get(i).getCosts());
            cellCosts.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellCTR = row.createCell(CTR_CELL);
            cellCTR.setCellValue(costCompany.get(i).getCtr());
            cellCTR.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));

            Cell cellCostOneConversation = row.createCell(COST_ONE_CONVERSATION_CELL);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(), COST_ONE_CONVERSATION_CELL, COST_ONE_CONVERSATION_CELL + 1));
            cellCostOneConversation.setCellValue(costCompany.get(i).getCostOneConversation());
            cellCostOneConversation.setCellStyle(getStyle(row.getRowNum(), conversation, costCompany.get(i).getCostOneConversation()));
        }

        costConversationRate = (costKeyGiveConversation / sendAll*100);
        changeCellFromRange(KEY_WAS_CLICKED_RN, keyWasClicked);
        changeCellFromRange(KEY_WAS_GAVE_COVERSATION, keyWasConversation);
        changeCellFromRange(KEY_WITHOUT_CONVERSATION, keyWithoutConversation);
        changeCellFromRange(KEY_WAS_GAVE_COVERSATION_RATE, (keyWasConversation /(double) keyWasClicked) * 100);
        changeCellFromRange(SEND_ALL_TWO, sendAll / 1000);
        changeCellFromRange(COST_CONVERSATION, costKeyGiveConversation / 1000);
        changeCellFromRange(COST_WITHOUT_CONVERSATION, costWithoutConversation / 1000 );
        changeCellFromRange(EFFECIENT_CONVERSATION_TWO, costConversationRate);

    }

    private CellStyle getStyle(int rowNumber, int conversation, double cost){
        CellStyle cellStyle = null;
        if(conversation == 0){
            if(rowNumber % 2 != 0){
                return cellStyle = ConfigExcel.STYLE_COST_NO_CONVERSTION;
            }else {
                return cellStyle = ConfigExcel.STYLE_COST_NO_CONVERSTION_1;
            }
        }else if(cost > queryClient.getMaxCpa()){
            if(rowNumber % 2 != 0){
                return cellStyle = ConfigExcel.STYLE_COST_EXPENSIVE_CONVERSATION;
            }else {
                return cellStyle = ConfigExcel.STYLE_COST_EXPENSIVE_CONVERSATION_1;
            }
        }else {
            if (rowNumber % 2 != 0) {
                return cellStyle = ConfigExcel.STYLE_COST_CHEAP_CONVERSATION;
            } else {
                return cellStyle = ConfigExcel.STYLE_COST_CHEAP_CONVERSATION_1;
            }
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
        Row r =  s.getRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        if(r.getRowNum() % 2 != 0){
            c.setCellStyle(ConfigExcel.STYLE_DEVICE_SIMPLE);
        }else {
            c.setCellStyle(ConfigExcel.STYLE_DEVICE);
        }
    }

    public void changeCellFromRange(String rangeName, int changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.getRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        if(r.getRowNum() % 2 != 0){
            c.setCellStyle(ConfigExcel.STYLE_DEVICE_SIMPLE);
        }else {
            c.setCellStyle(ConfigExcel.STYLE_DEVICE);
        }
    }

    public void changeCellFromRange(String rangeName, double changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.getRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        if(r.getRowNum() % 2 != 0){
            c.setCellStyle(ConfigExcel.STYLE_DEVICE_SIMPLE);
        }else {
            c.setCellStyle(ConfigExcel.STYLE_DEVICE);
        }
    }
}

package com.analytics.report.excel;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.entity.report.SourceVisited;
import com.analytics.report.excel.reports.*;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class CreateExcelReport {
    private String nameTemplate = "C:\\ftp\\report.xlsx";
    public static String pathToSaveReport = "C:\\ftp\\report1.xlsx"; //"\\ready\\report1.xlsx";
    public static XSSFWorkbook book;
    public static XSSFSheet sheetData, sheetReport, sheetRecommendation;
    public static XSSFSheet sheetStyle;
    private CreationHelper creationHelper;
    private SourceVisited sourceVisited;
    private QueryClient queryClient;
    private ExcelRecommendation excelRecommendation;


    public CreateExcelReport(QueryClient queryClient) {
        InputStream inputStream = null;
        book = null;
        excelRecommendation = new ExcelRecommendation();
        this.queryClient = queryClient;
        try {
            //create Excel book
            inputStream = new FileInputStream(nameTemplate);
            book = new XSSFWorkbook(inputStream);
            sheetData = book.getSheet("ChanelTrafic");
            sheetReport = book.getSheet("report");
            sheetStyle = book.getSheet("style");
            sheetRecommendation = book.getSheet("recommendation");
            clearSheet();
            FileOutputStream fileOutputStream = new FileOutputStream(pathToSaveReport);

            ExcelSourceVisited excelSourceVisited = new ExcelSourceVisited(queryClient, excelRecommendation);
            ExcelSourceDetail excelSourceDetail = new ExcelSourceDetail(queryClient, excelRecommendation);
            ExcelGoal excelGoal = new ExcelGoal(queryClient, excelRecommendation);
            ExcelDynamicConversation excelDynamicConversation = new ExcelDynamicConversation(queryClient, excelRecommendation);
            ExcelFunnel excelFunnel = new ExcelFunnel(queryClient, excelRecommendation);
            ExcelSearchOrContext excelSearchOrContext = new ExcelSearchOrContext(queryClient, excelRecommendation);
            ExcelRegion excelRegion = new ExcelRegion(queryClient, excelRecommendation);
            ExcelDevice excelDevice = new ExcelDevice(queryClient, excelRecommendation);
            ExcelUserAge excelUserAge = new ExcelUserAge(queryClient, excelRecommendation);
            ExcelMaleOrFemale excelMaleOrFemale = new ExcelMaleOrFemale(queryClient, excelRecommendation);
            ExcelWeek excelWeek = new ExcelWeek(queryClient, excelRecommendation);
            ExcelDayOfWeek excelDayOfWeek = new ExcelDayOfWeek(queryClient, excelRecommendation);
            ExcelCosts excelCosts = new ExcelCosts(queryClient, excelRecommendation);
            creationHelper = book.getCreationHelper();
            book.write(fileOutputStream);
            book.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clearSheet(){
        for(int i = 1; i <= sheetData.getLastRowNum(); i++){
            for(int j = 0; j < 43; j++){
                if(sheetData.getRow(i).getCell(j) != null){
                    Cell cell = sheetData.getRow(i).getCell(j);
                    sheetData.getRow(i).removeCell(cell);
                }
            }
        }
    }

    private Cell getCellFromRange(String rangeName){
        int namedCellIdx = book.getNameIndex(rangeName);
        Name aNamedCell = book.getNameAt(namedCellIdx);

        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        System.out.println(cells.length);
        for(int i = 0; i < cells.length; i++){
            Sheet s = book.getSheet(cells[i].getSheetName());
            Row r =  s.getRow(cells[i].getRow());
            c = r.getCell(cells[i].getCol());
        }
        return c;
    }

}

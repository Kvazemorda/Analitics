package com.analitics.report;

import com.analitics.entity.respons.ya.data.Dimension;
import com.analitics.entity.respons.ya.data.DimensionData;
import com.analitics.entity.respons.ya.data.SourceVisitedFromYa;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class PDFCreator {
    BaseFont times;
    final StandardChartTheme chartTheme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();

    public PDFCreator(SourceVisitedFromYa sourceVisitedFromYa) throws IOException, DocumentException {
        times = BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251",BaseFont.EMBEDDED);
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(DimensionData data: sourceVisitedFromYa.getData()){
            for(Dimension dimension: data.getDimensions()){
                for(List<Double> value: data.getMetrics()){
                    Double summ = 0.0;
                    for(Double eacheValue: value){
                        summ += eacheValue;
                    }
                    dataset.setValue(dimension.getName(),summ);
                    System.out.println(dimension.getName() + " " + summ);
                }
            }
        }
        JFreeChart chart = ChartFactory.createPieChart("Source", dataset, true, true, false);


        String path = "E://piechart.pdf";
        writeChartsToPDF(chart, 500, 500, path);
    }

    public void writeChartsToPDF(JFreeChart chart, int width, int height, String fileName){
        CategoryPlot category = chart.getCategoryPlot();


        PdfWriter pdfWriter = null;
        Document document = new Document();

        try {
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("тест русского", new Font(times, 14)));
            document.add(new Paragraph("test english"));

            PdfContentByte contentByte = pdfWriter.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(width, height);
            Graphics2D graphics2D = template.createGraphics(width, height, new DefaultFontMapper());
            Rectangle2D rectangle2D = new Rectangle2D.Double(0,0,width,height);
            chart.draw(graphics2D, rectangle2D);
            graphics2D.dispose();
            contentByte.addTemplate(template, 0, 0);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
    }
}

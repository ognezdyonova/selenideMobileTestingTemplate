package core;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Chart {
    private Map<String, Long> dataSource;

    public Chart(Map<String, Long> dataSource) {
        this.dataSource = dataSource;
        System.out.println(dataSource);

    }

    public Map<String, Long> getDataSource() {
        return dataSource;
    }

    public void lineChart() {

        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            XSSFSheet sheet = wb.createSheet("WB");
            Row row = sheet.createRow(0);

            row.createCell(0).setCellValue("Test request");
            row.createCell(1).setCellValue("Time (s)");


            int i = 1;
            for (Map.Entry<String, Long> entry : getDataSource().entrySet()) {
                String k = entry.getKey();
                double v = TimeUnit.MILLISECONDS.toSeconds(entry.getValue());

                Row rowData = sheet.createRow((short) i++);

                rowData.createCell(0).setCellValue(k);
                rowData.createCell(1).setCellValue(v);
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 2, 1, 26, 26);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("Perfomens of requests");
            chart.setTitleOverlay(false);

            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("Requests");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("Time(s)");

            XDDFDataSource<String> countries = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                    new CellRangeAddress(1, getDataSource().size(), 0, 0));

            XDDFNumericalDataSource<Double> population = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                    new CellRangeAddress(1, getDataSource().size(), 1, 1));

            XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);


            XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(countries, population);
            series2.setTitle("Perfom", null);
            series2.setSmooth(false);
            series2.setMarkerSize((short) 6);
            series2.setMarkerStyle(MarkerStyle.DOT);

            chart.plot(data);

            // Write output to an excel file
            String filename = "speed.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

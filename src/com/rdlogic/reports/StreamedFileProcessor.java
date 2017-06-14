package com.rdlogic.reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by admin on 6/14/2017.
 */
public class StreamedFileProcessor {

    private final String inDataPath, outBookPath, delim;
    private SXSSFWorkbook wb;
    private int window = 250;
    private String sheetName = "Worksheet";

    public StreamedFileProcessor(String inDataPath, String outBookPath, String delim) {
        this.inDataPath = inDataPath;
        this.outBookPath = outBookPath;
        this.delim = delim;
    }

    public void process() throws Exception {
        wb = new SXSSFWorkbook(window);
        Sheet sheet = wb.createSheet(sheetName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inDataPath)));
             FileOutputStream out = new FileOutputStream(outBookPath)){
            int r = 0;
            for (String line; (line = br.readLine())!=null;r++){
                Row row = sheet.createRow(r);
                String[] cells = line.split(Pattern.quote(delim));
                for (int c=0;c<cells.length;c++){
                    Cell cell = row.createCell(c);
                    cell.setCellValue(cells[c]);
                }

            }
            wb.write(out);
        }
        wb.dispose();
    }
}


package Flipkartt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Flipkart {

    String path = "C:\\Users\\akhil\\Desktop\\daata\\Fli.xlsx";
  
    //          MOBILE EXCEL SHEET
    public void WriteMobiles( List<String> names, List<String> prices) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        fis.close();

        Sheet sh = wb.getSheet("Mobile");
        if (sh == null) { sh = wb.createSheet("Mobile");}

        writeData(sh, names, prices, "Mobile name");
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.close();
        wb.close();

        System.out.println("Mobile sheet updated successfully");
        System.out.println("Total mobiles written: " + names.size());  }

    //           LAMP EXCEL SHEET

    public void WriteLamps(
            List<String> names,
            List<String> prices) throws IOException {

        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        fis.close();
        Sheet sh = wb.getSheet("Lamp");
        if (sh == null) {
            sh = wb.createSheet("Lamp");
        }

        writeData(sh, names, prices, "Lamp name");
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.close();
        wb.close();

        System.out.println("Lamp sheet updated successfully");
        System.out.println("Total lamps written: " + names.size());
    }
    //         COMMON EXCEL METHOD
    public void writeData(
            Sheet sh,
            List<String> names,
            List<String> prices,
            String heading) {

    	
        // CREATE HEADERS
        Row header = sh.getRow(0);
        if (header == null) {
            header = sh.createRow(0);
        }

        header.createCell(0).setCellValue("Sl.no");
        header.createCell(1).setCellValue(heading);
        header.createCell(2).setCellValue("Price");

        // CLEAR OLD DATA BUT KEEP HEADER
        for (int r = sh.getLastRowNum(); r >= 1; r--) {
            Row oldRow = sh.getRow(r);
            if (oldRow != null) { sh.removeRow(oldRow); } }

        // WRITE ALL RECORDS
        for (int i = 0; i < names.size(); i++) {
            Row row = sh.createRow(i + 1);
            // COLUMN A - SERIAL NUMBER
            Cell slno = row.createCell(0);
            slno.setCellValue(i + 1);

            // COLUMN B - PRODUCT NAME
            Cell name = row.createCell(1);
            name.setCellValue(names.get(i));

            // COLUMN C - PRICE
            Cell price = row.createCell(2);
            price.setCellValue(prices.get(i));
        }}}
   

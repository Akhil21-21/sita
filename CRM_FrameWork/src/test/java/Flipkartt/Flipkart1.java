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

public class Flipkart1 {

    public void WriteMobiles(
            List<String> names,
            List<String> prices) throws IOException {

        String path = "C:\\Users\\akhil\\Desktop\\daata\\Fli.xlsx";

        FileInputStream fis = new FileInputStream(path);

        Workbook wb = WorkbookFactory.create(fis);

        fis.close();

        // SELECT MOBILES SHEET

        Sheet sh = wb.getSheet("Mobiles");

        // CHECK SHEET

        if (sh == null) {
            wb.close();
            throw new IOException("Mobiles sheet not found in Excel");
        }

        // CLEAR OLD DATA, KEEP HEADER

        for (int r = sh.getLastRowNum(); r >= 1; r--) {

            Row oldRow = sh.getRow(r);

            if (oldRow != null) {
                sh.removeRow(oldRow);
            }
        }

        // WRITE ALL MOBILE DETAILS

        for (int i = 0; i < names.size(); i++) {

            Row row = sh.createRow(i + 1);

            // SERIAL NUMBER

            Cell slno = row.createCell(0);
            slno.setCellValue(i + 1);

            // MOBILE NAME

            Cell name = row.createCell(1);
            name.setCellValue(names.get(i));

            // MOBILE PRICE

            Cell price = row.createCell(2);
            price.setCellValue(prices.get(i));
        }

        // SAVE EXCEL

        FileOutputStream fos = new FileOutputStream(path);

        wb.write(fos);

        fos.close();
        wb.close();

        System.out.println("Excel updated successfully");
        System.out.println("Total mobiles written: " + names.size());
    }}
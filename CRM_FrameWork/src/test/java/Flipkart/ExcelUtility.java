


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtility {

    public String getData(int rowNum, int cellNum)
            throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\akhil\\Desktop\\daata\\Fli.xlsx");

        Workbook wb = WorkbookFactory.create(fis);

        Sheet sh = wb.getSheet("FLIP");

        Row row = sh.getRow(rowNum);

        Cell cell = row.getCell(cellNum);

        String data;

        
        if (cell.getCellType() == CellType.STRING) {
            data = cell.getStringCellValue();
        } else {
            data = String.valueOf((int)cell.getNumericCellValue());
        }

        wb.close();
        fis.close();

        return data;
    }
}

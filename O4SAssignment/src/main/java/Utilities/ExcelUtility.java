package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {


    XSSFCell cell;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public String readExcelData(String sheetName, String filePath,
                                String useCase) {

        try {

            workbook = new XSSFWorkbook(new FileInputStream(filePath));
            sheet = workbook.getSheet(sheetName);
            cell = findCell(sheet, useCase, true);

        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Either you have used same table(start or end) name in excel or you left column value empty..Please check your entry in excel");
            e.printStackTrace();
        }

        return cell.getStringCellValue();

    }


    public static XSSFCell findCell(XSSFSheet sheet, String text, boolean isUseCase) {

        XSSFCell dataCell = null;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {

                    if (text.equals(cell.getStringCellValue())) {

                        dataCell = sheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex() + 1);

                    }

                }

            }

        }

        return dataCell;

    }
}
package Utils;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public static String getCellData(String sheetName, int row, int col) {
		
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/TestData_ParaBank.xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			return sheet.getRow(row).getCell(col).toString();
		}catch(Exception e) {
			return "";
		}
				
	}

}

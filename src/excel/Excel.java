package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.*;

public class Excel {
	public static HSSFWorkbook readFile(String filename) throws IOException {
		return new HSSFWorkbook(new FileInputStream(filename));
	}
	
	public static void modifyValueOfTitoliFile(double value) throws IOException{
		
		HSSFWorkbook wb = Excel.readFile("SPREAD BTP.xls");
		HSSFSheet sheet = wb.getSheetAt(0);

		/*
		 * primo campo
		 */
		HSSFRow row = sheet.getRow(3);
		HSSFCell cell = row.getCell(5);
		System.out.println("vecchio valore: " + cell);
		cell.setCellValue(value);
		System.out.println("nuovo valore: " + cell);

		/*
		 * secondo campo
		 */
		row = sheet.getRow(17);
		cell = row.getCell(5);
		cell.setCellValue(value);

		/*
		 * terzo campo
		 */
		row = sheet.getRow(30);
		cell = row.getCell(5);
		cell.setCellValue(value);

		System.out.println("salvataggio...");
		wb.write(new FileOutputStream("SPREAD BTP.xls"));
		System.out.println("salvataggio completato");
		
	}

}

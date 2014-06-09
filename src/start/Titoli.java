package start;

import java.io.*;
import java.net.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import readFile.ReadFile;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import excel.*;

public class Titoli {


	public double getTitoliValue(String codiceIsin) throws IOException{
		
		String url = "http://www.borsaitaliana.it/borsa/obbligazioni/mot/btp/scheda.html?isin="+codiceIsin+"&lang=it";

		URL myURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();
		BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = read.readLine();
		while(line!=null) {
			if(line.contains("Ultimo Prezzo")){
				while(!line.contains("<tr>"))
					line = read.readLine();
				/*
				 * ho trovato la riga corrispondente che contiene la quotazione
				 */
				line = read.readLine();
				line = line.split(">")[1];
				line = line.split("<")[0];

				break;
			}
			line = read.readLine();
		}
		
		String output;
		output = line.split(",")[0];
		output += "." + line.split(",")[1];
		return Double.parseDouble(output);
	}

	
	public void modifyValueOfTitoliFile(double value) throws IOException{
		
		HSSFWorkbook wb = Excel.readFile("prova.xls");
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		HSSFCell cell = row.getCell(0);
		System.out.println(cell);
		cell.setCellValue(value);
		System.out.println(cell);
		wb.write(new FileOutputStream("prova.xls"));
	}
	
	
	public static void main(String [] args) throws BiffException, IOException, WriteException  {

		
		String codiceIsin = ReadFile.readFile("btp.txt");
		System.out.println(codiceIsin);

		double actualValue;
		Titoli t = new Titoli();
		
		actualValue = t.getTitoliValue(codiceIsin);
		
		System.out.println(actualValue);
		
		t.modifyValueOfTitoliFile(actualValue);
	}
}


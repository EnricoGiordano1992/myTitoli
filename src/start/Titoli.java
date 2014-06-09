package start;

import java.io.*;

import readFile.ReadFile;
import web.Web;
import excel.*;

public class Titoli {


	public static void main(String [] args) throws IOException {

		System.out.println("Inizio programma.");
		
		String codiceIsin = ReadFile.readFile("btp.txt");
		System.out.println("identificato titolo: " + codiceIsin);

		double actualValue;

		actualValue = Web.getTitoliValue(codiceIsin);

		System.out.println("quotazione attuale: " + actualValue);
		
		Excel.modifyValueOfTitoliFile(actualValue);
		
		System.out.println("Arresto.");
	}
}


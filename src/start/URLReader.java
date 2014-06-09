package start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class URLReader {



	public static void main(String [] args)  {

		try {

			String codiceIsin = "IT0003745541";
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
					System.out.println(line);
					
					break;
				}
				line = read.readLine();
			}
			

		} catch(MalformedURLException ex) {
			ex.printStackTrace();
		} catch(IOException ioex) {
			ioex.printStackTrace();
		}

	}
}


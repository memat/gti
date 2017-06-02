package teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class ImportaEndereco {

	public static void main(String[] args) {
		try {
			  //csv file containing data
			  String strFile = "C:\\Endereco.csv";
			  CSVReader reader = new CSVReader(new FileReader(strFile));
			  String [] nextLine;
			  int lineNumber = 0;
			  while ((nextLine = reader.readNext()) != null) {
			    lineNumber++;
			    int columnNumber = 0;
			    System.out.println("Linha: " + lineNumber);
			    
			    while ((nextLine = reader.readNext()) != null) {
			    	
			    }
			    // nextLine[] is an array of values from the line
			    //System.out.println(nextLine[4] + "etc...");
			  }
			 }catch (Exception e){
				 e.printStackTrace();
			 }

	}

}

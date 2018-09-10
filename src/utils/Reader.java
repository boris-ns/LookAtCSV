package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {

	private static BufferedReader reader;
	
	/**
	 * Reads just first line of the file (header) and returns 
	 * it as String[] array of tokens (column names)
	 */
	public static String[] readCSVHeader(String path) {
		String[] header = null;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			
			if (line == null)
				return null;
			
			header = line.split(",");
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while closing FileReader. File path: " + path);
			e.printStackTrace();
		}
		
		return header;
	}
	
	/**
	 * Skips first line of file (header) and reads all other lines.
	 * Parses each line into tokens and fills Object[][] array which is returned.
	 */
	public static ArrayList<ArrayList<String>> readCSVData(String path) {
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = null;

			reader.readLine(); // Skip first line - header
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(tokens));
				dataList.add(temp);
			}

			//data = convertArrayList(dataList);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while closing FileReader. File path: " + path);
			e.printStackTrace();
		}
		
		return dataList;
	}
	
	/**
	 * This method converts ArrayList<ArrayList<String>> to the regular Object[][] array.
	 */
	private static Object[][] convertArrayList(ArrayList<ArrayList<String>> list) {
		Object[][] array = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
		    ArrayList<String> row = list.get(i);
		    array[i] = row.toArray(new Object[row.size()]);
		}
		
		return array;
	}
}

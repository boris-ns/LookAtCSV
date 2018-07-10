package model;

import utils.Reader;

public class DataModel {

	private String[] header;
	private Object[][] data;
	
	public DataModel() {
		header = Reader.readCSVHeader("csv_test_files/countries.csv");
		data   = Reader.readCSVData("csv_test_files/countries.csv");
	}
	
	public String[] getHeader() {
		return header;
	}
	
	public Object[][] getData() {
		return data;
	}
}

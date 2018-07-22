package model;

import utils.Reader;

public class DataModel {

	private String[] header;
	private Object[][] data;
	
	public DataModel() {
	}

	public void readData(String path) {
		header = Reader.readCSVHeader(path);
		data   = Reader.readCSVData(path);
	}

	public void createEmptyModel(String header) {
		this.header = header.split(",");
		this.data = new Object[0][0];
	}

	public String[] getHeader() {
		return header;
	}
	
	public Object[][] getData() {
		return data;
	}

	public int getDataSize() {
		return data.length;
	}
}

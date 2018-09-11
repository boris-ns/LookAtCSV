package model;

import utils.Reader;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class DataModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> header;
	private ArrayList<ArrayList<String>> data;
	
	public DataModel() {
		header = new ArrayList<String>();
		data   = new ArrayList<ArrayList<String>>();
	}

	public void readData(String path) {
		String[] h = Reader.readCSVHeader(path);
		header = new ArrayList<String>(Arrays.asList(h));
		data   = Reader.readCSVData(path);
	}

	public void createEmptyModel(String header) {
		this.header = new ArrayList<String>(Arrays.asList(header.split(",")));
		this.data.clear();
	}

	public int getDataSize() {
		return data.size();
	}

	public ArrayList<ArrayList<String>> getData() {
		return data;
	}
	
	public ArrayList<String> getHeader() {
		return header;
	}

	public void addRow() {
		String[] row = new String[getColumnCount()];
		data.add(new ArrayList<String>(Arrays.asList(row)));
	}

	public void removeRow(int row) {
		data.remove(row);
	}

	public void addColumn(String columnName) {
		// TODO: fix
		header.add(columnName);

		for (int i = 0; i < data.size(); ++i) {
			data.get(i).add("");
		}
	}

	@Override
	public String getColumnName(int column) {
		return header.get(column);
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return header.size();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex).get(columnIndex);
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, (String) value);
	}
}

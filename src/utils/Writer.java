package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Writer {

    private static PrintWriter writer;

    public static void writeToFile(ArrayList<ArrayList<String>> data, String path) {
        StringBuilder line = new StringBuilder();

        try {
            writer = new PrintWriter(new FileWriter(path));
        } catch (IOException e) {
            System.out.println("Error! File not found -> " + path);
            e.printStackTrace();
        }

        for (ArrayList<String> list : data) {
            for (String str : list) {
                line.append(str + ",");
            }

            line.deleteCharAt(line.length() - 1);
            writer.println(line);
            line.delete(0, line.length());
        }

        writer.close();
    }
}

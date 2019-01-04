package utils;

import com.opencsv.CSVReader;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;
import java.io.FileReader;

/**
 * Created by Daniil on 26.05.2018.
 */
public class DataFileParser {


    public Boolean checkFileCSV(String path) {
        String fileName = path;
        File file = new File(fileName);
        if (file.canRead()) {return true;}
        else return false;
     }
    public DefaultTableModel FileParseCSV(String path){

        Object[] columnnames;
        CSVReader readerx = null;
        try {
            readerx = new CSVReader(new FileReader(path));
            List myEntries = readerx.readAll();
            columnnames = (String[]) myEntries.get(0);
            DefaultTableModel tableModel = new DefaultTableModel(columnnames, myEntries.size()-1);
            int rowcount = tableModel.getRowCount();
            for (int x = 0; x<rowcount+1; x++)
            {
                int columnnumber = 0;
                if (x>0)
                {
                    for (String thiscellvalue : (String[])myEntries.get(x))
                    {
                        tableModel.setValueAt(thiscellvalue, x-1, columnnumber);
                        columnnumber++;
                    }
                }
            }
            GlobalVars.TABLEDATA = tableModel;
          return tableModel;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package ActionHandlers;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Daniil on 08.06.2018.
 */
public class UploadButtonActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();
        Scanner in = null;

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".CSV Data Files", "csv");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File SelectedFile = chooser.getSelectedFile();
            try {
                in = new Scanner(SelectedFile);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //Вывод в консоль 10 первых строк данных
            int linenumber = 1;
            while (in.hasNextLine() && linenumber <= 10) { //MAX Read lines
                String line = in.nextLine();
                System.out.println(linenumber + ": " + line + "/n");
                linenumber++;
            }
            //Вывод в таблицу
            GlobalVars.CHARTPANEL.removeAll();

            DataFileParser dataParser = new DataFileParser();
            dataParser.FileParseCSV(SelectedFile.getPath());
            if (dataParser.FileParseCSV(SelectedFile.getPath()) != null) {
                JPanel panel = GlobalVars.MAINPANEL;
                JScrollPane JScrollpane = new JScrollPane(new JTable(dataParser.FileParseCSV(SelectedFile.getPath())));
                JScrollpane.setSize(panel.getSize());
                GlobalVars.TABLESP = JScrollpane;
                panel.add(JScrollpane, BorderLayout.CENTER);
                panel.setBackground(Color.RED);
            } else {
                JOptionPane.showMessageDialog(null, "Error occures during parsing. Data File format is incorrect", "Error:", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(dataParser.FileParseCSV(SelectedFile.getPath()).toString());
            // Создание Dataset для графика, вывести 500 значений
           final XYSeries NewGraph = new XYSeries("Preview");
           //for (int i =1; i< GlobalVars.TABLEDATA.getRowCount(); i++) {
               for (int i =1; i<= 500; i++) {
                   NewGraph.add(i, Integer.parseInt(GlobalVars.TABLEDATA.getValueAt(i,2).toString().replace('.','0')));
           }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(NewGraph);
            //  GlobalVars.XYCHARTDATASET.removeAllSeries();
            //Вывод в Chart
            JFreeChart chart = ChartFactory.createXYAreaChart("Chart","Iteration","Value",dataset, PlotOrientation.VERTICAL, false,false,false);

            //Check Box Panel__________________________________________________
            JPanel CheckBoxesPanel = new JPanel();
            CheckBoxesPanel.setLayout(new GridLayout(0, 1, 10, 10));

            int numberCheckBox = 5;
            JCheckBox[] checkBoxList = new JCheckBox[numberCheckBox];

            for(int i = 0; i < numberCheckBox; i++) {
                checkBoxList[i] = new JCheckBox("CheckBox" + i);
                CheckBoxesPanel.add(checkBoxList[i]);
            }
            CheckBoxesPanel.setBorder(new TitledBorder(new EtchedBorder(),"Inputs:"));
            GlobalVars.CHARTPANEL.removeAll();
            //_________________________________________________________
            //
            ChartPanel newChart = new ChartPanel(chart);
            GlobalVars.PANELEAST.remove(1);
            GlobalVars.CHARTPANEL.setLayout( new FlowLayout(FlowLayout.TRAILING));
            GlobalVars.CHARTPANEL.add(newChart);
            GlobalVars.CHARTPANEL.setBorder(new TitledBorder(new EtchedBorder(),"Preview"));
            GlobalVars.CHARTPANEL.add(CheckBoxesPanel);
            GlobalVars.PANELEAST.add(GlobalVars.CHARTPANEL);
            GlobalVars.PANELEAST.repaint();
            GlobalVars.PANELEAST.revalidate();


        }
    }
}
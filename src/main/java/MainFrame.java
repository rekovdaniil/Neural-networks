import ActionHandlers.UploadButtonActionListener;
import javafx.scene.control.ComboBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Daniil on 08.06.2018.
 */
//Конструктор формы основного меню по умолчанию
public class MainFrame implements ActionListener {

    JFrame MainMenu = new JFrame("Deep Learning Main Menu");
    JPanel PanelNorth = new JPanel();
    JPanel PanelSouth = new JPanel();
    JPanel PanelEast = new JPanel();
    JPanel PanelWest = new JPanel();
    JPanel PanelTable = new JPanel();
    JPanel ChartPanel = new JPanel();

    JButton UploadButton = new JButton();


    MainFrame()
    {
    //Сохраняем ссылки на обьекты
    GlobalVars.MAINFRAME = MainMenu;
    GlobalVars.MAINPANEL = PanelTable;
    GlobalVars.CHARTPANEL = ChartPanel;
    GlobalVars.PANELEAST = PanelEast;


    MainMenu.setLayout(new BorderLayout(10,10));


        //Test
        /*
        PanelNorth.setBackground(Color.BLUE);
        PanelEast.setBackground(Color.GREEN);
        PanelWest.setBackground(Color.RED);
        PanelSouth.setBackground(Color.YELLOW);
        PanelTable.setBackground(Color.BLACK);
        */
    // Добавление панелей
        MainMenu.add(PanelNorth, BorderLayout.NORTH);
        MainMenu.add(PanelEast, BorderLayout.EAST);
        MainMenu.add(PanelWest, BorderLayout.WEST);
        MainMenu.add(PanelSouth, BorderLayout.SOUTH);
        MainMenu.add(PanelTable, BorderLayout.CENTER);
        PanelTable.setLayout(new BorderLayout());
        PanelEast.setLayout(new GridLayout(2,2));
        ChartPanel.setLayout( new FlowLayout(FlowLayout.TRAILING));

    //Добавление спэйсеров и границ
        PanelNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PanelEast.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        PanelWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PanelSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PanelWest.setBorder(new LineBorder(Color.black));
        PanelSouth.setBorder(new LineBorder(Color.black));
        PanelNorth.setBorder(new LineBorder(Color.black));


        //Upload Button
        UploadButton.setText("Upload Data");
        ActionListener Upload = new UploadButtonActionListener();
        UploadButton.addActionListener(Upload);
        PanelWest.add(UploadButton);

    //Chart
        final XYSeries DefaultSeries = new XYSeries("Default");
        DefaultSeries.add(1, 0);
        DefaultSeries.add(2, 1);
        DefaultSeries.add(3, 2);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(DefaultSeries);

        JFreeChart chart = ChartFactory.createXYAreaChart("Chart","Moment","Value", dataset, PlotOrientation.VERTICAL, false,false,false);
        //GlobalVars.CHARTDISPLAY = chart;
        ChartPanel chartPanel = new ChartPanel(chart);
        ChartPanel.add(chartPanel,BorderLayout.CENTER);
        ChartPanel.setBorder(new TitledBorder(new EtchedBorder(),"Chart"));



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
        ChartPanel.add(CheckBoxesPanel);
        //_________________________________________________________
        //

        JPanel SettingsPanel = new JPanel();
        SettingsPanel.setLayout(new GridLayout(1, 2, 10, 10));
        SettingsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Settings:"));
        PanelEast.add(SettingsPanel);
        PanelEast.add(ChartPanel,1);

        //_________________________________________________________
        //

        JPanel LearmingTestValidationPannel = new JPanel();
        LearmingTestValidationPannel.setLayout(new GridBagLayout());
        LearmingTestValidationPannel.setBorder(new TitledBorder(new EtchedBorder(),"Data set:"));

        JLabel Step3Label = new JLabel("SAMPLES DISTRIBUTION");

        JLabel SamplesNumberLabel = new JLabel("Number of samples:");
        JLabel SamplesNumber = new JLabel("");

        JLabel TrainingLabel = new JLabel("Training samples:");
        JLabel ValitationLabel = new JLabel("Validation samples:");
        JLabel TestLabel = new JLabel("Testing samples:");
        JLabel ZLabel = new JLabel("Z-function column index:");
        JTextField ZField = new JTextField();



        //!!!!!!!!!!!! JLabel Step2Label = new JLabel("Select Z-function:");
       //!!!!!!!!!!!  ComboBox ZFunction = new ComboBox();
        JSlider TrainingSlider = new JSlider();
        TrainingSlider.setValue(70);
        JSlider ValidationSlider = new JSlider();
        ValidationSlider.setValue(15);
        JSlider TestSlider = new JSlider();
        TestSlider.setValue(15);

        GridBagConstraints GBC = new GridBagConstraints();
        GBC.fill = GridBagConstraints.HORIZONTAL;
        GBC.insets = new Insets(5,5,5,5);


                GBC.gridx = 0;
                GBC.gridy = 1;
                LearmingTestValidationPannel.add(SamplesNumberLabel,GBC);

                GBC.gridx = 1;
                GBC.gridy = 1;
                LearmingTestValidationPannel.add(SamplesNumber,GBC);



                GBC.gridx = 0;
                GBC.gridy = 0;
                LearmingTestValidationPannel.add(Step3Label,GBC);

                GBC.gridx = 0;
                GBC.gridy = 2;
                LearmingTestValidationPannel.add(TrainingLabel,GBC);


                GBC.gridx = 1;
                GBC.gridy = 2;
                LearmingTestValidationPannel.add(TrainingSlider,GBC);

                GBC.gridx = 0;
                GBC.gridy = 3;
                LearmingTestValidationPannel.add(ValitationLabel,GBC);

                GBC.gridx = 1;
                GBC.gridy = 3;
                LearmingTestValidationPannel.add(ValidationSlider,GBC);

                GBC.gridx = 0;
                GBC.gridy = 4;
                LearmingTestValidationPannel.add(TestLabel,GBC);

                GBC.gridx = 1;
                GBC.gridy = 4;
                LearmingTestValidationPannel.add(TestSlider,GBC);

                GBC.gridx = 0;
                GBC.gridy = 5;
                LearmingTestValidationPannel.add(ZLabel,GBC);

                GBC.gridx = 1;
                GBC.gridy = 5;
                LearmingTestValidationPannel.add(ZField,GBC);


        JPanel NetworkSettingsPannel = new JPanel();
        NetworkSettingsPannel.setLayout(new GridLayout(1, 2, 10, 10));
        NetworkSettingsPannel.setBorder(new TitledBorder(new EtchedBorder(),"Model architecture:"));

        //ADD Settings fields to MODEL
        

        SettingsPanel.add(LearmingTestValidationPannel);
        SettingsPanel.add(NetworkSettingsPannel);


        JLabel StatusLabel = new JLabel("Status:");
        JLabel CURRENTSTATUSLABEL = new JLabel("Starting up.............");

        PanelSouth.add(StatusLabel);
        PanelSouth.add(CURRENTSTATUSLABEL);

        PanelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));


        MainMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenu.setVisible(true);
    }





    public void actionPerformed(ActionEvent e) {
        //System.out.println("Action!");
    }


}

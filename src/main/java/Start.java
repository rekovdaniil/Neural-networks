import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniil on 26.05.2018.
 */
public class Start {
    public static void main(String[] args) {


   // MainFrame MF = new MainFrame();

        int[] LayersArray =  {2,3,2};
        NeuralNetwork TestNetwork = new NeuralNetwork(2,2,3, LayersArray,1);
        TestNetwork.SetRandomWeights();
        TestNetwork.GetWeights();
        System.out.println("!!!Beforestart!!!");
        TestNetwork.getTask();
        TestNetwork.feedForward();
        TestNetwork.fixOutError();
        TestNetwork.findErrors();
        TestNetwork.feedBackwars(0.5);
        System.out.println("!!!!!!!!AfterLearning!");
        TestNetwork.GetWeights();
        TestNetwork.GetNetwork();


    }
}

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Daniil on 20.12.2018.
 */
public class NeuralNetworkTest {
    int[] LayersArray =  {2,3,2};

    NeuralNetwork TestNetwork = new NeuralNetwork(2,2,3, LayersArray,1);


    @Test
    public void checkWeightsCreation() throws Exception {

        TestNetwork.SetRandomWeights();
        Assert.assertTrue(TestNetwork.GetWeights());

    }

    @Test
    public void checkTrainingSetsCreation() throws Exception {

        TestNetwork.SetRandomWeights();
        Assert.assertTrue(TestNetwork.GetWeights());

    }

    @Test
    public void FeedForward() throws Exception {
        //TestNetwork.getTask();
        TestNetwork.SetRandomWeights();
        TestNetwork.getTask();
        TestNetwork.feedForward();
        TestNetwork.fixOutError();
        TestNetwork.findErrors();
       // Assert.assertTrue(TestNetwork.GetWeights());
        TestNetwork.feedBackwars(0.1);
        Assert.assertTrue(TestNetwork.GetWeights());
        Assert.assertTrue(TestNetwork.GetNetwork());



    }

}

import java.lang.reflect.Array;

import static java.lang.StrictMath.exp;

/**
 * Created by Daniil on 20.12.2018.
 */
public class NeuralNetwork {
    public int NInputs;
    public int NOutputs;
    public int NLayers;
    public int NeuronsInLayer[];
    public double WeightsArray[][][][];
    public double [][][] NETWORK;
    public double OutError[];
    public double Etalons[];
    public int NSamples;


    NeuralNetwork(int NumberOfInputs, int NubmerOfOutputs, int NubmerOfLayers, int[] neuronsinlayer, int numberOfSamples)
    {
        NInputs = NumberOfInputs;
        NOutputs = NubmerOfOutputs;
        NLayers = NubmerOfLayers;
        NSamples = numberOfSamples;
        NeuronsInLayer = new int[neuronsinlayer.length];
        for (int i = 0; i < NeuronsInLayer.length; i++) {
            NeuronsInLayer[i] = neuronsinlayer[i];
        }

        CreateNetworkArray();
        CreateWeightsArray();
    }


    public void CreateWeightsArray()
    {
        WeightsArray  = new double [NLayers][3][][]; // номер слоя\ вес - 0, 1-ошибка, вход \номер нейрона Входящего слоя\ номер нейрона Выхлодного слоя

         for (int i = 0; i < NLayers-1; i++) {
             for (int j = 0; j < NeuronsInLayer[i]; j++) {
                 WeightsArray[i][0] = new double[NeuronsInLayer[i]][NeuronsInLayer[i + 1]]; //weight
                 WeightsArray[i][1] = new double[NeuronsInLayer[i]][NeuronsInLayer[i + 1]]; //error
                 WeightsArray[i][2] = new double[NeuronsInLayer[i]][NeuronsInLayer[i + 1]]; //input
                 for (int t = 0; t < NeuronsInLayer[i + 1]; t++)
                     for (int a = 0; a<=2; a++) {
                         WeightsArray[i][a][j][t] = 0;
                         WeightsArray[i][a][j][t] = 0;
                         WeightsArray[i][a][j][t] = 0;
                     }
             }
             }
            }




    public void CreateNetworkArray()
    {
         NETWORK  = new double[NLayers][2][]; // номер слоя, 0 - ошибка, 1 - вход , номер нейрона

        //создать массив необходимой длины под входы и ошибки для необходимого количества нейронов каждого слоя
        for(int i = 0; i<NETWORK.length; i++){
            NETWORK[i][0] = new double[NeuronsInLayer[i]];
            NETWORK[i][1] = new double[NeuronsInLayer[i]];
        }
        System.out.println("====================");

        for(int i = 0; i<NETWORK.length; i++){
            System.out.print("\nLayer["+i+"]");
            for(int j = 0; j<NETWORK[i][1].length; j++){
                System.out.print(NETWORK[i][1][j] + " ");
            }
        }
        System.out.println("\n====================");
    }


    public void SetRandomWeights() {
        for (int i = 0; i < NLayers-1; i++) {

            for (int j = 0; j < NeuronsInLayer[i]; j++) {
                for (int jout = 0; jout < NeuronsInLayer[i + 1]; jout++) {

                    WeightsArray[i][0][j][jout] = Math.random();
                }
            }
        }
    }
    public void SetEqualWeights(double Number)
    {
        for (int i = 0; i < NLayers; i++) {

            for (int j = 0; j < NeuronsInLayer[i]; j++) {
                for (int jout = 0; jout < NeuronsInLayer[i+1]; jout++) {
                    WeightsArray[i][0][j][jout] = Number;
                }
            }
        }
    }

    public boolean GetWeights() {
        for (int i = 0; i < NLayers - 1; i++) {

            for (int j = 0; j < NeuronsInLayer[i]; j++)
                for (int jout = 0; jout < NeuronsInLayer[i+1]; jout++) {
                    {
                        {
                            System.out.println("Layer:" + i + " X:" + j + " Y:"+jout+ " Weight:" + WeightsArray[i][0][j][jout] + "  Error:" + WeightsArray[i][1][j][jout]);
                        }
                    }
                }
        }
        return true;
    }

    public boolean GetNetwork() {
        System.out.println("-------------Net Inputs--------------");
        for(int i = 0; i<NETWORK.length; i++){ //cлои
            System.out.print("\nLayer["+i+"]");
            for(int j = 0; j<NETWORK[i][1].length; j++){ //нейрон в слое
                System.out.print(NETWORK[i][1][j] + " ");

            }
        }
        System.out.println("\n-------------Errors--------------");
        for(int i = 0; i<NETWORK.length; i++){ //cлои
            System.out.print("\nLayer["+i+"]");
            for(int j = 0; j<NETWORK[i][1].length; j++){ //нейрон в слое
                //System.out.print(NETWORK[i][1][j] + " ");
                System.out.print("Neuron " + j + " Error: " + NETWORK[i][0][j] + " || ");
            }
        }
        System.out.println("\n---------------------------------");

        return true;
    }


    public void Start()
    {
        //Training();
        //Predict();
    }
    public void Training(int NumberOfSamples, double accuracy)
    {
        double CurrentAccuracy = 0.00;
        int iteration = 0;
        while(CurrentAccuracy<accuracy && iteration < 10)
        {
            iteration = iteration + 1;
            getTask();
            feedForward();
            fixOutError();
            findErrors();
            feedBackwars(0.1);
        }

    }
    public void Predict(int NumberOfSamples)
    {
    getTask();
    feedForward();
    fixOutError();
    findErrors();
    feedBackwars(0.1);

    }
    public void getTask()
    {
        int InputLayer = NeuronsInLayer[0];
        for(int in = 0; in < InputLayer;in++) {
            NETWORK[0][1][in] = 2;
        }
        OutError  = new double [NeuronsInLayer[NLayers-1]];
        Etalons  = new double [NeuronsInLayer[NLayers-1]];
        Etalons[0] = 1;
        Etalons[1]= 0.5;
    }
    public void feedForward()
    {
        int InputLayer = 0;
        int OutputLayer = 0;
        for (int i = 0; i < NeuronsInLayer.length-1; i++) { //Определение количества нейронов между слоями
            InputLayer = NeuronsInLayer[i];
            OutputLayer = NeuronsInLayer[i+1];

            for (int out = 0; out < OutputLayer; out++)  // цикл по выходному слою
            {
                NETWORK[i+1][1][out] = 0;
                for(int in = 0; in < InputLayer;in++)
                {
                   // System.out.println("in=" + in + " out=" + out  );
                    //System.out.println(NETWORK[i+1][1][out] +"= "+ NETWORK[i+1][1][out] +" + "+ WeightsArray[i][0][in][out]+ "*"+ NETWORK[i][1][in] );
                    NETWORK[i+1][1][out] = NETWORK[i+1][1][out] + WeightsArray[i][0][in][out]*NETWORK[i][1][in];
                }
                NETWORK[i+1][1][out] =1/(1+exp(-1*NETWORK[i+1][1][out])); // !!!функция активации
            }
        }

    }
    public void fixOutError()
    {
        for ( int i = 0; i < NeuronsInLayer[NLayers-1];i++ )
        {
            NETWORK[NLayers-1][0][i] = Etalons[i] - NETWORK[NLayers-1][1][i];
         //   OutError[i] =  Etalons[i] - NETWORK[NLayers-1][1][i];
          // System.out.println(OutError[i]);
        }
    }
    public void  findErrors()
    {
        //Учесть нейрон смещения!
        int InputLayer = 0;
        int OutputLayer = 0;
        for (int i = NeuronsInLayer.length-1; i > 0 ; i--) { //Определение количества нейронов между слоями
            System.out.println("!!!" + i);
            InputLayer = NeuronsInLayer[i-1];
            OutputLayer = NeuronsInLayer[i];
            for (int in = 0; in< InputLayer; in++)// цикл по входному слою
                {
                    NETWORK[i-1][0][in] = 0;
                    System.out.println("++++");

                    for (int out = 0; out < OutputLayer; out++)  // цикл по выходному слою
                {
                    NETWORK[i-1][0][in] = NETWORK[i-1][0][in] + WeightsArray[i-1][0][in][out]*NETWORK[i][0][out];
                    System.out.println(NETWORK[i-1][0][in] + "+" + WeightsArray[i-1][0][in][out] + " * " + NETWORK[i][0][out]  );

                }
            }

        }

    }

    public void feedBackwars(double learningRate)
    {
        int InputLayer = 0;
        int OutputLayer = 0;
        for (int i = 0; i < NeuronsInLayer.length-1; i++) { //Определение количества нейронов между слоями
            InputLayer = NeuronsInLayer[i];
            OutputLayer = NeuronsInLayer[i + 1];

            for (int out = 0; out < OutputLayer; out++)  // цикл по выходному слою
            {
                for (int in = 0; in < InputLayer; in++) {

                    WeightsArray[i][0][in][out] = WeightsArray[i][0][in][out] + learningRate * NETWORK[i + 1][0][out] * NETWORK[i + 1][1][out] * (1 - NETWORK[i + 1][1][out]) * NETWORK[i][1][in];
                }
            }

        }

    }

}

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

/**
 * Created by michall on 16-01.
 */
public class Arrythmia {
    public static void main (String[] args) {
        DataSetParser parser = DataSetParser.getInstance();
        parser.preprocessData("data.dat","data2.dat");
        //wczytac wszystko
        DataSet trainingSet = DataSet.createFromFile("data2.dat", 279, 1, ",", false);
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 279,2, 1);
        myMlPerceptron.learn(trainingSet);
        System.out.println("heh");
        // learn the training set
        //myMlPerceptron.learn(trainingSet);
        
    }
}

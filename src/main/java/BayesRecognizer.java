import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by tarekray on 14/02/16.
 */
public class BayesRecognizer {
    HashMap<Integer, Probability[][]> memory ;
    Probability[][] allProbability ;
    public BayesRecognizer(HashMap<Integer, Probability[][]> memory, Probability[][] allProbability) {
        this.memory = memory ;
        this.allProbability = allProbability ;
    }


    public void recognize(File testImage, File testLabel) {
        try {
            Scanner imageScanner = new Scanner(testImage);
            Scanner labelScanner = new Scanner(testLabel);
            Probability correctness = new Probability() ;
            while(imageScanner.hasNext())
            {
                int CORRECT_ANSWER = labelScanner.nextInt() ;
                ArrayList<String> image = Parser.getImage(imageScanner) ;
                int[][] result = ImageProcessor.process(image) ;
                double[] probability = new double[10] ;
                for(int i = 0 ; i < 10 ; i++)
                {
                    probability[i] = bayes(i, result) ;
                }

                int indexMax = getMax(probability);
                String str = " INCORRECT " ;
                if(CORRECT_ANSWER==indexMax)
                {
                    correctness.incrementCount();
                    str = " CORRECT " ;
                }
                correctness.incrementTotal();

                System.out.println(str + "Guess " +indexMax+ " CORRECT NUMBER IS "+CORRECT_ANSWER+" RATIO "+ correctness.getRatio()) ;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //what is probability of this number being the correct number; knowing the given input
    private double bayes(int number, int[][] result) {
        double product = 1 ;
        for(int j = 0 ; j < result.length ; j++)
        {
            for(int i = 0 ; i < result[0].length ;i++)
            {
                if(result[j][i]==1)
                {
                    product=product*memory.get(number)[j][i].getProbability()/allProbability[j][i].getProbability() ;
                }
            }
        }
        return  product ;
    }

    private int getMax(double[] probability) {
        double max = -1 ;
        int indexMax = -1 ;

        for(int i = 0 ; i < 10 ; i++)
        {
           if(probability[i] > max){
               max = probability[i] ;
               indexMax=i ;
           }
        }
        return indexMax;
    }


}

/**
 * Created by tarekray on 14/02/16.
 */
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.HashMap ;

public class Memory {
        public static Probability[][] allProbability       ;
    public HashMap<Integer, Probability[][]> memory ;

    public Memory()
    {
        allProbability = new Probability[Parameters.y][Parameters.x] ;
        initializeAllProbability();
        memory = new HashMap<Integer, Probability[][]>() ;
    }


    public void learn(MatchEntity matchEntity)
    {
        int value = matchEntity.getValue() ;
        ArrayList<String> image = matchEntity.getImage() ;
        int[][] result = ImageProcessor.process(image) ;
        Probability[][] probabilities ;
        if(memory.containsKey(value)) {
            probabilities = memory.get(value) ;
        }
        else {
            probabilities = new Probability[Parameters.y][Parameters.x] ;
            initializeProbabilities(probabilities);
        }
        integrate(probabilities, result) ;
        memory.put(value, probabilities) ;
    }



    private void integrate(Probability[][] probabilities, int[][] results) {
        for(int j = 0 ; j < probabilities.length ; j++){
            for(int i = 0 ; i <probabilities[0].length ; i++)
            {
                Probability probability = probabilities[j][i] ;
                int result = results[j][i] ;

                if(result==1)
                {
                    probability.incrementCount();
                    allProbability[j][i].incrementCount();
                }
                probability.incrementTotal();
                allProbability[j][i].incrementTotal();
            }
        }
    }

    private void initializeProbabilities(Probability[][] probabilities) {
        for(int y = 0 ; y < Parameters.y ; y++)
        {
            for(int x= 0 ; x<Parameters.x;x++)
            {
                probabilities[y][x]=new Probability();
            }
        }
    }
    private void initializeAllProbability() {
        for(int y = 0 ; y < allProbability.length ; y++){
            for(int x = 0 ; x <allProbability[0].length;x++){
                allProbability[y][x] = new Probability() ;
            }
        }
    }

}

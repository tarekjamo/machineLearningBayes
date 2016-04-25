import org.junit.Test;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by tarekray on 14/02/16.
 */
public class BayesRecogniserTest {

    @Test
    public void simple_test()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File debugImage = new File(classLoader.getResource("digitdata/debugimages").getFile());
        File debugLabel = new File(classLoader.getResource("digitdata/debuglabels").getFile());

        MachineLearner ml = new MachineLearner();
        ml.learn(debugImage,debugLabel);

        assertThat(ml.statisticsLearned.memory.get(0)[0][0].getRatio()).isEqualTo(1) ;
        assertThat(ml.statisticsLearned.memory.get(0)[0][Parameters.x-1].getRatio()).isEqualTo(0) ;
        assertThat(ml.statisticsLearned.memory.get(1)[0][0].getRatio()).isEqualTo(0.5);


        assertThat(ml.statisticsLearned.cumulativeProbability[0][0].getRatio()).isGreaterThan(0.65).isLessThan(0.67) ;
    }


    @Test
    public void launch()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File trainingimage = new File(classLoader.getResource("digitdata/trainingimages").getFile());
        File traininglabel = new File(classLoader.getResource("digitdata/traininglabels").getFile());

        File validationImage = new File(classLoader.getResource("digitdata/validationimages").getFile());
        File validationLabel = new File(classLoader.getResource("digitdata/validationlabels").getFile());

        File testImage = new File(classLoader.getResource("digitdata/testimages").getFile());
        File testLabel = new File(classLoader.getResource("digitdata/testlabels").getFile());


        MachineLearner ml = new MachineLearner();
          ml.learn(trainingimage, traininglabel) ;
          ml.learn(validationImage,validationLabel);

        BayesRecognizer recognizer = new BayesRecognizer(ml.statisticsLearned.memory, ml.statisticsLearned.cumulativeProbability) ;
        recognizer.recognize(testImage,testLabel);
        assertThat(recognizer.efficiency).isGreaterThan(0.75) ;

    }

}

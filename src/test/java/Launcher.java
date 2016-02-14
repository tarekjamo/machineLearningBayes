import org.junit.Test;

import java.io.File;

/**
 * Created by tarekray on 14/02/16.
 */
public class Launcher {

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
          ml.learn(testImage, testLabel) ;
          ml.learn(validationImage,validationLabel);

        BayesRecognizer recognizer = new BayesRecognizer(ml.memory.memory, ml.memory.allProbability) ;
        recognizer.recognize(testImage,testLabel);

    }

}

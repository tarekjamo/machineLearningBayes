import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by tarekray on 14/02/16.
 */
public class MachineLearner {

    public Memory memory ; // hash map -> countclassprobability

    public  void learn(File imageFile, File labelFile) {

        memory = new Memory();
        ArrayList<MatchEntity> matchEntities = new ArrayList<MatchEntity>() ;
        try {
            Scanner imageScanner = new Scanner(imageFile);
            Scanner labelScanner = new Scanner(labelFile);
           while(labelScanner.hasNext())
            {
                int value = labelScanner.nextInt() ;
                ArrayList<String> image = Parser.getImage(imageScanner) ;
                MatchEntity matchEntity = new MatchEntity(value,image) ;
                matchEntities.add(matchEntity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        save(matchEntities) ;

        matchEntities.stream().forEach(e->memory.learn(e));

    }

    private void print() {
        for(int index = 0 ; index<=9;index++) {

            System.out.println("_________________________" + index);
            Probability[][] p = memory.memory.get(index);

            for (int j = 0; j < p.length; j++) {
                for (int i = 0; i < p[0].length; i++) {
                    System.out.print(" " + p[j][i].getProbability() + " ");
                }
                System.out.println();
            }
        }
    }


    private static void save(ArrayList<MatchEntity> matchEntities) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("toDelete.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for(int i =60 ; i < matchEntities.size() ; i++) {
            writer.write(matchEntities.get(i).toString() + "\n");
        }
        writer.close();
    }
    private static void printMatchEntities(ArrayList<MatchEntity> matchEntities) {
      for(int i =60 ; i < matchEntities.size() ; i++) {
          matchEntities.get(i).print();
      }
    }




}

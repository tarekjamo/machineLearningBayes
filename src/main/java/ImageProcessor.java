import java.util.ArrayList;

/**
 * Created by tarekray on 14/02/16.
 */
public class ImageProcessor {


    public static  int[][] process(ArrayList<String> image) {
        Pair[][] count = new Pair[Parameters.y][Parameters.x] ;

        initializeCount(count);
        mapImageToArrayCounter(image, count);
        return getResults(count);
    }

    private static void mapImageToArrayCounter(ArrayList<String> image, Pair[][] count) {
        int ychunk = image.size()/Parameters.y +1;
        int xchunk = (image.get(0).length())/Parameters.x +1;
        for(int y = 0 ; y < image.size() ; y++)
        {
            String str = image.get(y) ;

            for(int x = 0 ; x < str.length() ; x++)
            {
                int yindex = y/ychunk ;
                int xindex = x/xchunk ;
                //   System.out.println("y = " +yindex+" ; x = "+xindex);
                count[yindex][xindex].incrementTotal();
                if(str.charAt(x)!=' ')
                {
                    count[yindex][xindex].incrementBlack();
                }
            }
        }
    }

    private static int[][] getResults(Pair[][] count) {
        int[][] result = new int[Parameters.y][Parameters.x] ;

        for(int y = 0 ; y < Parameters.y ; y++)
        {
            for(int x = 0 ; x < Parameters.x ; x++)
            {
                if(count[y][x].getRatio()>Parameters.threshold) {
                    result[y][x] = 1;
                }
            }
        }


        return result;
    }

    private static void initializeCount(Pair[][] count) {
        for(int y = 0 ; y < count.length ; y++) {
            for(int x = 0 ; x<count[0].length;x++)
            {
                count[y][x] = new Pair() ;
            }
        }
    }
}

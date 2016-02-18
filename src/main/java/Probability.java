/**
 * Created by tarekray on 14/02/16.
 */
public class Probability {
    private int count ;
    private int total ;

    public double getProbability()
    {
        if(total!=0) {
            return ((float)count) / total;
        }
        else {
            return 0;
        }
        }

    public double getRatio()
    {
      return getProbability();
    }


    public void incrementCount()
    {
        this.count = count+1 ;
    }

    public void incrementTotal()
    {
        this.total = total+1 ;
    }
}

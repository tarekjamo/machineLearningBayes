/**
 * Created by tarekray on 14/02/16.
 */
class Pair
{
    int black ;
    int total ;
    public Pair()
    {
        this.black = 0 ;
        this.total = 0 ;
    }
    public void incrementTotal()
    {
        total= total+ 1 ;
    }
    public void incrementBlack()
    {
        black=black+1 ;
    }
    public double getRatio()
    {
        if(total!=0) {
            return ((float) black) / total;
        }
        else{
            return 0;
        }
    }

}
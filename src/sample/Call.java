package sample;

public class Call {
    private int start;
    public double tRand, oRand;

    public Call(int number, double tRand, double oRand){

        start = number;
        this.tRand = tRand;
        this.oRand = oRand;
    }

    public int getStart(){
        return start;
    }

}

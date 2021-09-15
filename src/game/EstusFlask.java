package game;

public class EstusFlask{
    private int numOfEstusFlask;
    private final int MAX_NUM_OF_ESTUS_FLASK = 3;

    public EstusFlask() {
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }

    public int getNumOfEstusFlask() {
        return numOfEstusFlask;
    }

    public int getMaxNumOfEstusFlask(){
        return MAX_NUM_OF_ESTUS_FLASK;
    }

    public void decrementNumberOfEstusFlask(){
        numOfEstusFlask -= 1;
    }
}

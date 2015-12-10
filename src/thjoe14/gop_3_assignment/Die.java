package thjoe14.gop_3_assignment;

public class Die {

    int die;
    int dieSides = 6;
    
    public void throwDie() {
        die = (int) (Math.random() * dieSides) + 1;
    }

    public int getValue() {
        return die;
    }
}

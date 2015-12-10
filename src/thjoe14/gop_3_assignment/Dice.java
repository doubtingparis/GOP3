
package thjoe14.gop_3_assignment;


public class Dice
    {

        Die die1 = new Die();
        Die die2 = new Die();

        //Roll
        public void throwDice()
        {
            die1.throwDie();
            die2.throwDie();
        }
        //Return value
        public int getDice()
        {
            return die1.getValue()+die2.getValue();
        }
        //Two identical
        public boolean isMatch()
        {
            if (die1.getValue() == die2.getValue())
                return true;
            else
                return false;
        }
    }

package thjoe14.gop_3_assignment;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int pos;
    private int round;
    private int money = MonopolyConstants.START_MONEY;
    private List<OwnableField> ownsList = new ArrayList<>();

    public Player(String name, int pos, int round) {
        this.name = name;
        this.pos = pos;
        this.round = round;
    }

    public void move(int steps){//Gets new position
        pos = pos + steps;//add dice value
        if (pos > 39){//If past field id 39
            pos = pos - 40;//retract
            round++;//round +1
            add(MonopolyConstants.PASSING_START); //Bonus for at passere start
        }
    }

    public void buyField(OwnableField ownable) {
        retract(ownable.getPrice());// Uses pay method
        ownsList.add(ownable);
    }
    private void retract(int number) {
        this.money = money - number;
    }
    
    private void add(int number) {
        this.money = money + number;
    }
    
    public void payRent(OwnableField field) {
        retract(field.rent);// Uses retract method
    }
    
    public void recieveRent(OwnableField field) {
        add(field.rent);
    }
    
    public void payTax(taxField field) {
        retract(field.getTax());// Uses retract method
    }
    
    public int getMoney() {
        return money;
    }
    
    public void gotoJail(){
        this.pos = MonopolyConstants.JAIL_POS;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public int getRound() {
        return round;
    }

}

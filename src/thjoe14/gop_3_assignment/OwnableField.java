package thjoe14.gop_3_assignment;

public abstract class OwnableField implements FieldInterface {

    String name;
    int number;
    int price;
    int rent;
    Player owner = null;

    protected OwnableField(int rent, String name, int number, int price) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.rent = rent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }
    
    public int getRent(){
        return rent;
    }
    
    public boolean hasOwner(){
        if(owner == null)
            return false;
        else
            return true;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    @Override
    public void consequense(Player poorPlayer) {
        if(!hasOwner()){//Hvis der ingen ejer er
            //Hvis player har penge til det
            if(poorPlayer.getMoney() >= this.price){
                poorPlayer.buyField(this);//Køb
                this.setOwner(poorPlayer);
                System.out.println(poorPlayer.getName() + " has bought " + this.getName() + " for " + this.price + "$");
            } else {
                System.out.println(poorPlayer.getName() + " does not have enough money to buy " + this.name);
            }
        } else { //Hvis der er en ejer
            poorPlayer.payRent(this);//Betal leje
            this.owner.recieveRent(this);//Ejer modtager penge
            System.out.println(poorPlayer.getName() + " has paid " + this.rent + "$ in rent to " + this.owner.getName());
        }
    }

    @Override
    public String toString() {
        return "OwnableField{" + "name=" + name + ", number=" + number
                + ", price=" + price + ", owner=" + owner + '}';
    }
}

package thjoe14.gop_3_assignment;

public class taxField implements FieldInterface {

    private String name;
    private int number;
    private int tax;

    public taxField(int tax, String name, int number) {
        this.name = name;
        this.number = number;
        this.tax = tax;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return number;
    }
    
    public int getTax(){
        return tax;
    }

    @Override
    public void consequense(Player poorPlayer) {
        if(poorPlayer.getMoney() >= this.tax){
            poorPlayer.payTax(this);
            System.out.println(poorPlayer.getName() + " has paid a tax of " + this.tax);
        } else {
            poorPlayer.gotoJail();
            System.out.println(poorPlayer.getName() + "cannot pay tax and has been sent to jail!");
        }
    }


}

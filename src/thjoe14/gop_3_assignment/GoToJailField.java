package thjoe14.gop_3_assignment;

public class GoToJailField implements FieldInterface {

    private String name;
    private int number;

    public GoToJailField(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void consequense(Player poorPlayer) {
        poorPlayer.gotoJail();
        System.out.println(poorPlayer.getName() + " has been sent to jail!");
    }

    @Override
    public String toString() {
        return "GoToJailField{" + "name=" + name + ", number=" + number + '}';
    }

}

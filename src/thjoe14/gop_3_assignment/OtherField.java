
package thjoe14.gop_3_assignment;

public class OtherField implements FieldInterface{
    
    private int number;
    private String name;
    
    public OtherField(String name, int number) {
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
        System.out.println("Extra content not yet implemented");
    }
}

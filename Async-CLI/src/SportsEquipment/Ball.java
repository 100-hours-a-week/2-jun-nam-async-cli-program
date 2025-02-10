package SportsEquipment;

public class Ball extends SportsEquipment{
    float diameter;
    String sportsType;
    public Ball() {
    }
    public Ball(String name, float price) {
        super.name = name;
        super.price = price;
    }

    public Ball(String name){
        super.name = name;
    }
    public void printAction() {
        System.out.println("Woof!");
    }

    public void printInfo() {

    }
}

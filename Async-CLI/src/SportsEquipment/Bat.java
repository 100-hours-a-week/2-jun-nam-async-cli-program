package SportsEquipment;
import Category.Category;
public class Bat extends StrikingImplement{
    String sportsType;
    public Bat(String name, Category category, float price, float length, String material, String type, String sportsType){
        super.name = name;
        super.category = category;
        super.price = price;
        super.length = length;
        super.material = material;
        super.type = type;
        this.sportsType = sportsType;
    }

    @Override
    public void printInfo() {
        System.out.println("Name: " + super.name);
        System.out.println("Category: " + super.category);
        System.out.println("Price: $" + super.price);
        System.out.println("Length: " + super.length +"cm");
        System.out.println("Material: " + super.material);
        System.out.println("Type: " + super.type);
        System.out.println("SportsType: " + this.sportsType);
    }
}

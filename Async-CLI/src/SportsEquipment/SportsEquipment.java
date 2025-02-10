package SportsEquipment;
import Category.Category;
abstract public class SportsEquipment {
    String name;
    Category category;
    float price;

    public SportsEquipment(){

    }
    public SportsEquipment(String name, Category category, float price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }
    public float getPrice(){
        return this.price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    abstract public void printInfo();
}

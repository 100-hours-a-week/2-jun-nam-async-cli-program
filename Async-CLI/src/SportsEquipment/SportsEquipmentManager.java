package SportsEquipment;

import Coupon.Coupon;
import Category.Category;

import java.util.ArrayList;

public class SportsEquipmentManager {
    private ArrayList<SportsEquipment> equipments = new ArrayList<SportsEquipment>();


    public SportsEquipmentManager(){
        equipments.add(new Baseball("b55", Category.Ball, 10.99f, 12, "Baseball"));
        equipments.add(new TennisBall("t41", Category.Ball, 5.99f, 10, "Tennis"));
        equipments.add(new Bat("T100", Category.StrikingImplement, 45.99f, 100, "aluminium", "Bat", "Baseball"));
        equipments.add(new Bat("RQ80", Category.StrikingImplement, 45.99f, 100, "aluminium", "Racket", "Tennis"));
    }
    public SportsEquipment getSportsEquipment(int index){
        return this.equipments.get(index);
    }

    public String getEquipmentName(int index){
        return this.equipments.get(index).name;
    }

    public int getAvailableItemSize(){
        return this.equipments.size();
    }

}

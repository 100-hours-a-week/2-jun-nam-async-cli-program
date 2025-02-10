package User;

import java.util.ArrayList;

import Category.Category;
import SportsEquipment.*;
import Coupon.*;
public class User implements Runnable{
    public boolean couponStatus;
    private Coupon redeemedCoupon;
    private ArrayList<SportsEquipment> cart;
    public User() {
        this.couponStatus = false;
        this.cart = new ArrayList<SportsEquipment>();
    }
    public void receivedCoupon(Coupon tmp){
        this.couponStatus = true;
        this.redeemedCoupon = tmp;
    }

    public boolean getUserCouponStatus(){
        return this.couponStatus;
    }

    public void printCoupon(){
        System.out.println(getUserCoupon().getCouponType().toString() + " " + getUserCoupon().discountAmount + "%");
    }
    public void addToCart(SportsEquipment item){
        this.cart.add(item);
    }

    public void applyCartToUser(ArrayList<SportsEquipment> cart){
        this.cart = cart;
    }
    public ArrayList<SportsEquipment> getCart(){
        return this.cart;
    }
    public void printCartItems(){
        int cnt = 1;
        for (SportsEquipment sportsEquipment : cart) {
            System.out.println(cnt + ".");
            sportsEquipment.printInfo();
            System.out.println();
            cnt++;
        }
    }

    public float getTotalBill(){
        float sum = 0;
        for (SportsEquipment sportsEquipment : cart) {
            sum += sportsEquipment.getPrice();
        }
        return sum;
    }

    public Category getCouponType(){
        return redeemedCoupon.getCouponType();
    }

    public int getCartSize(){
        return this.cart.size();
    }

    public Coupon getUserCoupon(){
        return this.redeemedCoupon;
    }


    public boolean hasCouponAvailableItem(){
        for (SportsEquipment sportsEquipment : cart) {
            if(this.getCouponType().equals(sportsEquipment.getCategory()))
                return true;
        }
        return false;
    }

    public void applyCoupon(int index){
        float price = this.cart.get(index).getPrice();
        float priceAfterDiscount =  Math.round(((100 - this.redeemedCoupon.discountAmount) * price)) / 100.0f;
        cart.get(index).setPrice(priceAfterDiscount);
        System.out.println(price + " " + cart.get(index).getPrice());
    }
    public Category getCartItemCategory(int index){
        return this.cart.get(index).getCategory();
    }

    public void getCouponAvailableItem(Category type){
        int cnt = 1;
        for (SportsEquipment sportsEquipment : cart) {
            if(sportsEquipment.getCategory() == type){
                System.out.println(cnt + ".");
                sportsEquipment.printInfo();
                cnt++;
            }
        }
    }

    public void applyRedeemedCouponToUser(Coupon coupon){
        this.redeemedCoupon = coupon;
        this.couponStatus = true;
    }

    @Override
    public void run(){

    }
}

package Coupon;

import Category.Category;

public class Coupon {
    Category couponType;

    public int discountAmount;
    public Coupon(){
        double randomValue = Math.random();
        int type = (int)(randomValue * Category.values().length);
        Category[] categories = Category.values();
        this.couponType = categories[type];
        int amount = (int)(randomValue*3) + 1;
        this.discountAmount = amount * 10;
    }
    public void printCoupon(){
        System.out.println(this.couponType + "\n" + discountAmount);
    }

    public Category getCouponType(){
        return this.couponType;
    }
}

package Coupon;

import Coupon.Coupon;
public class CouponMananger implements Runnable{
    private Coupon coupon;

    public CouponMananger(){

    }

    @Override
    public void run(){
            this.coupon = new Coupon();
    }

    public Coupon getRedeemedCoupon(){
        return this.coupon;
    }
}

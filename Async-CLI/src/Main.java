import java.io.IOException;
import java.util.*;

import InputHandler.InputHandler;
import MenuManager.MenuManager;
import SportsEquipment.*;
import User.User;
import Coupon.Coupon;
import Cart.Cart;
import Coupon.CouponMananger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static User userinfo = new User();
    static Scanner sc = new Scanner(System.in);
    static InputHandler inputHandler = new InputHandler(sc);
    static SportsEquipmentManager sportsEquipmentManager = new SportsEquipmentManager();
    static Cart cart = new Cart();
    static MenuManager menuManager = new MenuManager(sc, sportsEquipmentManager);
    static final Lock couponLock = new ReentrantLock();
    static final Lock userLock = new ReentrantLock();
    static final Lock cartLock = new ReentrantLock();
    static CouponMananger couponMananger = new CouponMananger();

    public static void main(String[] args) throws IOException, InterruptedException {
        int UserInput;

        while(true){
            menuManager.showMainMenu();
            UserInput = inputHandler.mainMenuHandler();


            if(UserInput == 1){
                while(true){
                    menuManager.showItemMenu();
                    int totalItems = sportsEquipmentManager.getAvailableItemSize()+1;
                    UserInput = inputHandler.itemMenuHandler(totalItems);
                    if(UserInput != totalItems){
                        menuManager.showItemInfoMenu(UserInput-1);
                        boolean addToCart = inputHandler.addItemHandler();
                        if(addToCart){
                            //userinfo.addToCart(sportsEquipmentManager.getSportsEquipment(UserInput-1));
                            Thread cartThread = new Thread(cart);
                            cartLock.lock();
                            cart.addItem(sportsEquipmentManager.getSportsEquipment(UserInput-1), userinfo);
                            cartThread.start();
                            cartThread.join();
                            cartLock.unlock();
                        }
                    }
                    else{
                        if(userinfo.getCartSize() != 0){
                            boolean hasCoupon = menuManager.showPurchaseMenu(userinfo);
                            if(hasCoupon){
                                boolean flag = true;
                                while(true){
                                    UserInput = inputHandler.purchaseMenuHandler(userinfo.getCartSize());

                                    flag = userinfo.getCartItemCategory(UserInput-1).equals(userinfo.getCouponType());
                                    if(!flag){
                                        System.out.println("Inappropriate item");
                                        userinfo.getCouponAvailableItem(userinfo.getCouponType());

                                    }
                                    else {
                                        userinfo.applyCoupon(UserInput-1);
                                        break;
                                    }
                                }

                            }
                            float totalPrice = menuManager.showPriceMenu(userinfo);
                            float charge = inputHandler.priceHandler(totalPrice);
                            charge = Math.round(charge * 100) / 100.0f;
                            System.out.println("\nYour Change is : $" + charge + ". Have a nice day!");
                        }
                        break;
                    }
                }
                break;
            }
            else if (UserInput == 2){
                if(!userinfo.getUserCouponStatus()){
                    //userinfo.receivedCoupon(new Coupon());

                    Thread userThread = new Thread(() -> userinfo.applyRedeemedCouponToUser((couponMananger.getRedeemedCoupon())));
                    if(couponLock.tryLock()){
                        Thread couponThread = new Thread(couponMananger);
                        try {
                            couponThread.start();
                            couponThread.join();

                            userThread.start();
                            userThread.join();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        } finally {
                            couponLock.unlock();
                        }
                    }
                    else{
                        System.out.println("Coupon operation is currently in progress. Please try again later.");
                    }

                    System.out.println("\nCoupon redeemed");
                    System.out.println("-------------------------");
                    userinfo.printCoupon();
                    System.out.println("-------------------------\n");

                }
                else{
                    System.out.println("Coupon already redeemed\n");
                }
            }
            else{
                break;
            }
        }
    }

}

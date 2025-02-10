package MenuManager;
import SportsEquipment.SportsEquipmentManager;
import SportsEquipment.SportsEquipment;
import User.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class MenuManager {
    private final SportsEquipmentManager SportsEquipmentManager;
    private Scanner sc;
    private volatile boolean animationInterrupt = false;
    public MenuManager(Scanner scanner, SportsEquipmentManager SportsEquipmentManager) {
        this.sc = scanner;
        this.SportsEquipmentManager = SportsEquipmentManager;
    }

    private void typingEffect(String text, int delay){
        for (char c : text.toCharArray()) {
            if(animationInterrupt) {
                System.out.print("\r" + text);
                return;
            }
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
    public void showMainMenu(){
//        System.out.println("\nKakaoTech Bootcamp Assignment 1");
//        System.out.println("-------------------------\n");
//        System.out.println("1 - Explore Items");
//        System.out.println("2 - Redeem Coupon");
//        System.out.println("3 - Quit\n");
//        System.out.println("-------------------------");
//        System.out.print("Your Input - ");

        String mainMenuText = "\nKakaoTech Bootcamp Assignment 1\n" +
                              "-------------------------\n\n" +
                              "1 - Explore Items\n" +
                              "2 - Redeem Coupon\n" +
                              "3 - Quit\n\n" +
                              "-------------------------";

        Thread typingAnimation = new Thread(() -> typingEffect(mainMenuText, 50));
        typingAnimation.start();


        Thread inputThread = new Thread(() -> {
            sc.nextLine();
            animationInterrupt = true;
        });
        inputThread.start();

        try {
            typingAnimation.join();
            inputThread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        animationInterrupt = false;
        System.out.print("\nYour Input - ");
    }
    public void showItemMenu(){
//        System.out.println("\nAvailable Items");
//        System.out.println("-------------------------\n");
//        int cnt = 1;
//        for(int i = 0; i < SportsEquipmentManager.getAvailableItemSize(); i++){
//            System.out.println(cnt + " - " + SportsEquipmentManager.getEquipmentName(i));
//            cnt++;
//        }
////        System.out.println("3 - Bat");
////        System.out.println("4 - Raquet");
//
//        System.out.println(cnt + " - Go to PurchaseMenu\n");
//        System.out.println("-------------------------");
//        System.out.print("Your Input - ");
        sc.nextLine();
        int cnt = 1;
        String showItemMenuText = "\nAvailable Items\n" +
                "-------------------------\n\n";

        StringBuilder items = new StringBuilder();
        for (int i = 0; i < SportsEquipmentManager.getAvailableItemSize(); i++) {
            items.append(cnt).append(" - ").append(SportsEquipmentManager.getEquipmentName(i)).append("\n");
            cnt++;
        }

        showItemMenuText += items.toString() + cnt + " - Go to PurchaseMenu\n\n" + "-------------------------";
        String finalItemMenuText = showItemMenuText;
        Thread itemMenuAnimation = new Thread(() -> typingEffect(finalItemMenuText, 50));
        itemMenuAnimation.start();
        sc.nextLine();
        animationInterrupt = true;

        try{
            itemMenuAnimation.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        animationInterrupt = false;
        System.out.print("\nYour Input - ");
    }
    public void showItemInfoMenu(int userInput) {
        SportsEquipment selectedItem = SportsEquipmentManager.getSportsEquipment(userInput);
        if (selectedItem != null) {
            System.out.println("\nItem Info");
            System.out.println("-------------------------");
            selectedItem.printInfo();
            System.out.println("-------------------------");
        }
    }

    public boolean showPurchaseMenu(User userinfo){
        System.out.println("\nCart Info");
        System.out.println("-------------------------");
        userinfo.printCartItems();
        System.out.println("-------------------------\n");
        if(userinfo.getUserCouponStatus() && userinfo.hasCouponAvailableItem()){
            System.out.println("\nAvailable Coupon");
            System.out.println("-------------------------");
            userinfo.printCoupon();
            System.out.println("-------------------------");
            return true;
        }
        return false;
    }

    public float showPriceMenu(User userinfo){
        if(userinfo.getUserCouponStatus() && userinfo.hasCouponAvailableItem()) {
            System.out.println("\nCart Info");
            System.out.println("-------------------------");
            userinfo.printCartItems();
            System.out.println("-------------------------\n");
        }
        System.out.print("Amount you have to pay : $");
        float amount = userinfo.getTotalBill();
        System.out.println(amount);
        return userinfo.getTotalBill();
    }
}

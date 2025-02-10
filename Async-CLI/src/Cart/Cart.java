package Cart;

import java.io.IOException;
import java.util.ArrayList;
import User.User;
import SportsEquipment.SportsEquipment;
import java.util.LinkedList;
import java.util.Queue;
import User.User;
public class Cart implements Runnable{
    private SportsEquipment sportsEquipment;
    private ArrayList<SportsEquipment> cart;
    private final Queue<SportsEquipment> cartQueue;
    private User user;

    public Cart(){
        cartQueue = new LinkedList<>();
    }

    public void addItem(SportsEquipment item, User user) {
            this.user = user;
            cartQueue.add(item);
            System.out.println("[Cart] Item added to cart queue: " + item.getName());

    }

    @Override
    public void run(){
            try {
                this.sportsEquipment = cartQueue.poll();
                this.cart = user.getCart();
                this.cart.add(this.sportsEquipment);
                Thread userThread = new Thread(() -> user.applyCartToUser(this.cart));
                userThread.start();
                userThread.join();
                System.out.println("[Cart Thread] Item moved to user's cart: " + this.sportsEquipment.getName());
            }
            catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }
}

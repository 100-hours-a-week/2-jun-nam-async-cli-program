package InputHandler;

import java.util.Scanner;
import User.User;
public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner sc){
        this.scanner = sc;
    }

    public int mainMenuHandler(){
        int selection;

        while(true){
            try{
                selection = scanner.nextInt();

                if(selection < 1 || selection > 3){
                    throw new IllegalArgumentException("Invalid input! Please enter a number between 1 and 3.");
                }

                break;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();
                System.out.print("Your Input - ");
            }
        }
        return selection;
    }

    public int itemMenuHandler(int size){
        int selection;

        while(true){
            try{
                selection = scanner.nextInt();

                if(selection < 1 || selection > size){
                    throw new IllegalArgumentException("Invalid input! Please enter a number between 1 and " + size + ".");
                }

                break;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();
                System.out.print("Your Input - ");
            }
        }
        return selection;
    }

    public int purchaseMenuHandler(int size){
        int selection;

        while(true){
            System.out.print("Which item would you like to apply coupon? - ");
            try{
                selection = scanner.nextInt();

                if(selection < 1 || selection > size){
                    throw new IllegalArgumentException("Invalid input! Please enter a number between 1 and " + size + ".");
                }

                break;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();

            }
        }

        return selection;
    }

    public float priceHandler(float amount){
        float selection;

        while(true){
            System.out.print("You pay - $");
            try{
                selection = scanner.nextFloat();

                if(selection < amount){
                    throw new IllegalArgumentException("Invalid input! Your money is not emough");
                }

                break;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
        return selection - amount;
    }

    public boolean addItemHandler(){
        String selection;

        while(true){
            System.out.print("Add to Cart? (Y/N) - ");
            try{
                selection = scanner.next();

                if(!(selection.equalsIgnoreCase("Y") || selection.equalsIgnoreCase("N"))){
                    throw new IllegalArgumentException("Invalid input! Please enter Y or N");
                }

                break;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        if(selection.equalsIgnoreCase("Y"))
            return true;
        else
            return false;
    }
}

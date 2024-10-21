
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thaycacac
 */
class ExceptionCar extends Exception {

    public ExceptionCar() {
    }

    public ExceptionCar(String message) {
        super(message);
    }
}

public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static String checkInputString() throws ExceptionCar {
        while (true) {
            String result = in.nextLine();
            if (result.length() == 0) {
                System.out.println("Not empty");
                System.out.print("ENTER AGAIN: ");
            } else {
                return result;
            }
        }
    }

    private static int checkInputInt() throws ExceptionCar {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                if (result <= 0) {
                    System.out.println("Price greater than zero");
                    System.out.print("ENTER AGAIN: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException ex) {
                System.out.println("Price is digit");
                System.out.print("ENTER AGAIN: ");
            }

        }
    }

    private static boolean checkYN() throws ExceptionCar {
        while (true) {
            String result = checkInputString();
            if (result.length() == 1) {
                char resultChar = result.charAt(0);
                if (resultChar == 'y' || resultChar == 'Y') {
                    return true;
                }
                if (resultChar == 'n' || resultChar == 'N') {
                    return false;
                }
            }
            System.err.println("Re-input");
        }
    }

    private static boolean checkCarExist(Car car, String color, int price,
            String today) throws ExceptionCar {
        String[] listColor = car.getColer();
        int[] listPrice = car.getPrice();
        String[] listSold = car.getSoldOn();
        
        boolean check = false, noColor = false;
        while (true) {
            if (!color.equalsIgnoreCase("no color")) {
                for (int i = 0; i < listColor.length; i++) {
                    System.out.println(listColor[i]);
                    if (color.equalsIgnoreCase(listColor[i])) {
                        check = true;
                        break;
                    }
                }
            } else {
                check = true;
                noColor = true;
            }
            
            if (check == true) {
                break;
            }
            System.out.println("Color Car does not exist");
            System.out.print("Color: ");
            color = checkInputString();
        }
        
        check = false;
        while (check == false) {
            for (int i = 0; i < listPrice.length; i++) {
                if ((noColor == true && price == listPrice[i] - 100)
                        || (noColor == false && price == listPrice[i])) {
                    check = true;
                    break;
                }
            }
            
            if (check == true) 
                break;
            System.out.println("Price Car does not exist");
            System.out.print("Price: ");
            price = checkInputInt();            
        }
        
        check = false;
        while (check == false) {
            for (int i = 0; i < listSold.length; i++) {
                if (today.equalsIgnoreCase(listSold[i])) {
                    check = true;
                    break;
                }
            }
            
            if (check == false) {
                System.out.println("Car can't sell today");
                System.out.print("Today: ");
                today = checkInputString();
            } else {
                break;
            }
        }
        
        return true;
    }

    private static void addCar(ArrayList<Car> lc) {
        String[] listColorAudi = {"White", "Yellow", "Orange"};
        int[] listPriceAudi = {5500, 3000, 4500};
        String[] listSoldDayAudi = {"Friday", "Sunday", "Monday"};
        lc.add(new Car("Audi", listColorAudi, listPriceAudi, listSoldDayAudi));

        String[] listColorMercedes = {"Green", "Blue", "Purple"};
        int[] listPriceMercedes = {5000, 6000, 8500};
        String[] listSoldDayMercedes = {"TueDay", "Saturday", "Wednesday"};
        lc.add(new Car("Mercedes", listColorMercedes, listPriceMercedes,
                listSoldDayMercedes));

        String[] listColorBMW = {"Pink", "Red", "Brown"};
        int[] listPriceBMW = {2500, 3000, 3500};
        String[] listSoldDayBMW = {"Monday", "Sunday", "Thursday"};
        lc.add(new Car("BMW", listColorBMW, listPriceBMW, listSoldDayBMW));
    }

    private static boolean checkNameCar(ArrayList<Car> lc, String name, String color,
            int price, String today) throws ExceptionCar {
        boolean check = false;
        for (int i = 0; i < lc.size(); i++) {
            if (name.equalsIgnoreCase(lc.get(i).getNameCar())) {
                if (checkCarExist(lc.get(i), color, price, today)) {
                    System.out.println("Sell Car");
                    System.out.print("Do you want find more?(Y/N): ");
                    if (!checkYN()) {
                        return false;
                    }
                } else {
                    System.out.println("Car break");
                }
                check = true;
                break;
            }
        }
        if (check == false) {
            System.out.println("Can't find car.");
        }
        return true;
    }

    private static void display() throws ExceptionCar {
        ArrayList<Car> lc = new ArrayList<>();
        addCar(lc);
        System.out.println("Input information of car");
        while (true) {
            System.out.print("Name: ");
            String name = checkInputString();
            System.out.print("Color: ");
            String color = checkInputString();
            System.out.print("Price: ");
            int price = checkInputInt();
            System.out.print("Today: ");
            String today = checkInputString();
            if (!checkNameCar(lc, name, color, price, today)) {
                return;
            }
        }
    }

    public static void main(String[] args) throws ExceptionCar {
        display();
    }
}
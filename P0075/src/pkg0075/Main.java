package pkg0075;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    private Manager manager = new Manager();
    private Scanner scanner = new Scanner(System.in);
    private validation validation = new validation();

    private void display() {
        ArrayList<Account> la = new ArrayList<>();
        while (true) {
            System.out.println("1. Add user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = validation.checkIntLimit(1, 3);
            switch (choice) {
                case 1:
                    manager.addAccount(la);
                    break;
                case 2:
                    manager.login(la);
                    break;
                case 3:
                    return;
            }
        }
    }

    public void run() {
        display();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}

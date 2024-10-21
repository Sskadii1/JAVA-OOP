/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0075;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class validation {
    
       public static final Scanner in = new Scanner(System.in);
    public static final String PHONE_VALID = "^\\d{9,10}$";
    
    public static String checkInputPhone() {
        return checkInput(PHONE_VALID);
    }
    
    
    
    public static final String EMAIL_VALID = "^[0-9A-Za-z+_.%]+@[0-9A-Za-z.-]+\\.[A-Za-z]{2,4}$";
    
      public static String checkInputEmail() {
        return checkInput(EMAIL_VALID);
    }

    
    

    public static int checkIntLimit(int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(in.nextLine());
                if (n < min || n > max) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException ex) {
                System.err.println("Re-input");
            }
        }
    }

    
    
    
    public static String checkInputDate() {
        while (true) {
            try {
                String result = in.nextLine().trim();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(result);
                if (result.equalsIgnoreCase(format.format(date))) {
                    return result;
                } else {
                    System.err.println("Re-input");
                }
            } catch (ParseException ex) {
                System.err.println("Re-input");
            }
        }
    }

    
    
    
    public static String checkInput(String regex) {
        while (true) {
            String result = in.nextLine().trim();
            if (result.matches(regex)) {
                return result;
            } else {
                System.err.println("Re-input");
            }
        }
    }

    
    
    
    public static String checkInputString() {
        return checkInput(".+");
    }

    
    
    
    

    
    
    
  
    
    
    
    public static String checkInputUsername(ArrayList<Account> la) {
        while (true) {
            String result = checkInputString();
            for (Account account : la) {
                if (result.equalsIgnoreCase(account.getUsername())) {
                    System.err.println("Username exist!!!");
                    break;
                }
            }
            return result;
        }
    }

    
    
    
}

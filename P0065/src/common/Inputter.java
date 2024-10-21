package common;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class Inputter {

    public Scanner sc = new Scanner(System.in);

    public double getDouble(String msg, double min, double max, String err1, String err2) {
        while (true) {
            try {
                System.out.print(msg);
                double n = Double.parseDouble(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println(err1);
            } catch (NumberFormatException ex) {
                System.err.println(err2);
            }
        }
    }

    public String getString(String msg, String pattern, String err) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (s.matches(pattern)) {
                return s;
            }
            System.err.println(err);
        }
    }
    
    public void displayNotice(String notice) {
        System.out.println(notice);
    }
    
    public void displayErr(String err) {
        System.err.println(err);
    }

}

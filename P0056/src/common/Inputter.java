package common;

import controller.WorkerHandler;
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
    
    public int getInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("Must from: " + min + " -> " + max);
            } catch (NumberFormatException ex) {
                System.err.println("Wrong format");
            }
        }
    }

    public double getDouble(String msg, double min, double max) {
        while (true) {
            try {
                System.out.print(msg);
                double n = Double.parseDouble(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("Must from: " + min + " -> " + max);
            } catch (NumberFormatException ex) {
                System.err.println("Wrong format");
            }
        }
    }

    public String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (!s.isEmpty()) {
                return s;
            }
            System.err.println("Empty string is not allowed");
        }
    }
    
    public String getCode(String msg, String err,
            WorkerHandler wList, int mode) {
        while (true) {
            System.out.print(msg);
            String id = sc.nextLine();
            if (!id.isEmpty()) {
                if ((mode == 1 && wList.getWorker(id) == null)
                        || (mode == 2 && wList.getWorker(id) != null))
                    return id;
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

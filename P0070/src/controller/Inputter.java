/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Inputter {
    
    public Scanner sc = new Scanner(System.in);
    
    public int getInt(int min, int max) {
        while (true) {
            try {
                System.out.print("Choose: ");
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
    
    public void displayNotice(String notice) {
        System.out.println(notice);
    }
    
    public void displayErr(String err) {
        System.err.println(err);
    }
    
}

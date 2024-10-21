package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.Task;
/**
 *
 * @author Admin
 */
public class Inputter {
    
    Scanner sc = new Scanner(System.in);
    
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
    
    public String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.err.println("Do not enter empty string");
        }
    }

    public Date getDate(String msg) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        df.setLenient(false);
        while (true) {
            System.out.print(msg);
            try {
                String date = sc.nextLine().trim();
                return df.parse(date);
            } catch (ParseException ex) {
                System.err.println("Please enter correct format yyyy/MM/dd");
            } 
        }
    } 
    
    public String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(date);
    }

    public String getTaskType(String msg) {
        int tasktypeID = getInt(msg, 1, 4);
        String result = "";
        switch (tasktypeID) {
            case 1:
                result = "Code";
                break;
            case 2:
                result = "Test";
                break;
            case 3:
                result = "Design";
                break;
            case 4:
                result = "Review";
                break;
        }
        
        return result;
    }

    public double getDouble(String msg, String err, double min, double max) {
        double result;
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input.matches("^(\\d+\\.([5]|[0]))$")) {
                try {
                    result = Double.parseDouble(input);
                    if (min <= result && result <= max) {
                        return result;
                    }
                    System.err.println(err);                    
                } catch (NumberFormatException ex) {
                    System.err.println("Wrong format");
                }
            }
        }    
    }
    
    public boolean checkDuplicate(TaskManager tm, 
            String date, String assignee, double planFrom, double planTo) {
        ArrayList<Task> taskList = tm.taskList;
        boolean isExist = false;
        for (Task task : taskList) {
            if (date.equals(task.getDate()) && assignee.equals(task.getAssignee())) {
                if (planTo < task.getPlanFrom() || planFrom > task.getPlanTo()) {
                    isExist = false;
                } else {
                    isExist = true;
                    break;
                }
            }
        }
        
        return isExist;
    }
    
}

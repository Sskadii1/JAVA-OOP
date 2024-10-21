/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Inputter;
import controller.WorkerHandler;
import java.util.List;
import model.SalaryHistory;
import model.Worker;

/**
 *
 * @author Admin
 */
public class WorkerView {
    
    Inputter inputter = new Inputter();
 
    private final String[] options = {
        "Add worker",
        "Up salary",
        "Down salary",
        "Display information salary",
        "Exit"
    };
    
    public void displayMenu() {
        System.out.println("======== Worker Management =========");
        for (int i = 0; i < options.length; i++)
            System.out.println((i+1) + ". " + options[i]);
    }
    
    public Worker inputWorker(WorkerHandler wh) {
        String id = inputter.getCode("Enter code: ", "Code must be unique and not empty", wh, 1);                    
        String name = inputter.getString("Enter name: ");
        int age = inputter.getInt("Enter age: ", 18, 50);
        double salary = inputter.getDouble("Enter salary: ", 1, Double.MAX_VALUE);
        String workLocation = inputter.getString("Enter work location: ");
        return new Worker(id, name, age, salary, workLocation);
    }
    
    public void displayInformationSalary(List<SalaryHistory> shList) {
        System.out.println("-------------------- Display Information Salary -----------------------");                
        System.out.println(String.format
        ("%-3s | %-6s | %-5s | %-7s | %-7s | %-11s"
                , "Code", "Name", "Age", "Salary", "Status", "Date"));        
        for (SalaryHistory sh : shList) {
            System.out.println(sh);
        }
    }    
    
}

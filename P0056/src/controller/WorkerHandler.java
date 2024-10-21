/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Inputter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.SalaryHistory;
import model.Worker;
import view.WorkerView;

/**
 *
 * @author Admin
 */
public class WorkerHandler {
    
    List<Worker> wList = new ArrayList<>();
    List<SalaryHistory> shList = new ArrayList<>();
    Inputter inputter = new Inputter();
    WorkerView workerView = new WorkerView();        
    
    
    
    private void addWorker(String id, 
            String name,
            int age, 
            double salary, 
            String workLocation) {        
        wList.add(new Worker(id,
                name, 
                age, 
                salary, 
                workLocation));
        inputter.displayNotice("Add worker successfully!");
    }
    
    
    
    
    
    private void changeSalary(String status) {
        
        if (wList.isEmpty()) {
            inputter.displayErr("Worker List is empty! Please add informations");
            return;
        }
        
        if (status.equals("UP")) {
            
            System.out.println("------- Up Salary --------");
        } else if (status.equals("DOWN")) {
            
            System.out.println("------- Down Salary --------");
        }
        
        String id = inputter.getCode("Enter code: ", "Code must exist in worker list", this, 2);
        
        double amount = inputter.getDouble("Enter amount to change: ", 0, Double.MAX_VALUE);
        
        Worker w = getWorker(id);
        
        double update = 0;
        
       if (status.equals("UP")) {
        update = w.getSalary() + amount;
    } else if (status.equals("DOWN")) {
        if (w.getSalary() >= amount) { // Thêm điều kiện này để kiểm tra lương mới không thấp hơn lương hiện tại
            update = w.getSalary() - amount;
        } else {
            inputter.displayErr("New salary cannot be lower than the current salary!");
            return;
        }
    }
        
        w.setSalary(update);
        
        shList.add(new SalaryHistory(id, w.getName(), w.getAge(), w.getSalary(),
                status, java.time.LocalDate.now())); 
        
        inputter.displayNotice("Update worker's salary successfully!");
    }
    
    public Worker getWorker(String id) {
        
        for (Worker w : wList) {
            
            if (w.getId().equals(id)) return w;
            
        }
        return null;
    }
    
    
    
    private List<SalaryHistory> getSortedInformationSalary() {
        
        Collections.sort(shList, new Comparator<SalaryHistory>() {
            @Override
            public int compare(SalaryHistory o1, SalaryHistory o2) {
                
                int cmp = o1.getId().compareTo(o2.getId());
                
                if (cmp > 0) return 1;
                
                if (cmp == 0) {
                    
                    if (o1.getUpdateSalary() > o2.getUpdateSalary()) return 1;
                    
                    return -1;
                    
                }
                return -1;
            }
            
        });
        
        return shList;
    }   
    
    
    
    public void execute() {     
    
        int choice;
        do {
            workerView.displayMenu();
            choice = inputter.getInt("Choose: ", 1, 5);
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("--------- Add Worker ----------");
                    Worker w = workerView.inputWorker(this); 
                    addWorker(w.getId(), w.getName(), 
                            w.getAge(), w.getSalary(), w.getWorkLocation());
                    break;
                case 2:
                    changeSalary("UP");
                    break;
                case 3:
                    changeSalary("DOWN");
                    break;
                case 4:
                    if (shList.isEmpty()) {
                        inputter.displayErr("Salary History "
                                + "List is empty! Please add informations");
                    } else {
                        shList = getSortedInformationSalary();
                        workerView.displayInformationSalary(shList);
                    }
                    break;
                case 5:
                    break;
            }
            
            System.out.println();
        } while (choice != 5);     
    }
    
}

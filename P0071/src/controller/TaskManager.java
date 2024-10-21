package controller;

import model.Task;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.TaskView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class TaskManager {
    
    int id = 0;
    ArrayList<Task> taskList = new ArrayList<>();
    Inputter inputter = new Inputter();
    TaskView view = new TaskView();
    
    public void execute() {        
        int choice;
        do {
            view.displayMenu();
            choice = inputter.getInt("Choose: ", 1, 4);
            switch (choice) {
                // 1. Add Task
                case 1:
                    System.out.println("---------------- Add Task ----------------");
                    Task task = view.inputTask(this);                    
                    if (task != null) {
                        try {
                            id = addTask(task.getRequirementName(), task.getAssignee(), task.getReviewer(),                        
                                    task.getTaskTypeID(), task.getDate(), task.getPlanFrom(), task.getPlanTo());
                            if (id != -1) {
                                System.out.println("Add successful!");
                            } else {
                                System.err.println("Duplicate!!");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    } else {
                        System.err.println("Duplicated!");
                    }
                    break;
                
                    
                    
                // 2. Delete Task
                case 2:
                    if (taskList.isEmpty()) {
                        System.out.println("Task list is empty!!");
                    } else {
                        System.out.println("---------------- Delete Task ----------------");
                        String taskId = inputter.getString("ID: ");
                        try {
                            deleteTask(taskId);                            
                        } catch (Exception ex) {
                            System.out.println("ID is not exist!");
                        }                        
                    }
                    break;
                
                // 3. Display Task
                case 3:
                    if (taskList.isEmpty()) {
                        System.out.println("Task list is empty!!");
                    } else {
                        System.out.println("---------------- Task ----------------");
                        view.displayDataTasks(taskList);
                    }
                    break;
                
                // 4. Exit
                case 4:
                    break;
            }
            
            System.out.println();
        } while (choice != 4);
    }
    
    
    
    public int addTask(String requirementName, String assignee, String reviewer, 
            String taskTypeID, String date, double planFrom, double planTo) throws Exception {
        if (!inputter.checkDuplicate(this, date, assignee, planFrom, planTo)) {
            id++;
            taskList.add(new Task(id, taskTypeID,
                    requirementName, date, planFrom, planTo, assignee, reviewer));        
            return id;
        }
        return -1;
    }
    
    
    
    public void deleteTask(String id) throws Exception {
        for (Task task : taskList) {
            if (Integer.parseInt(id) == task.getTaskID()) {
                taskList.remove(task);
                System.out.println("Delete successful!");
                this.id--;
                break;
            }
        }
    }
    
    
    public ArrayList<Task> getDataTasks() {
        return taskList;
    }
    
}

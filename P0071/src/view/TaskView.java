package view;

import controller.Inputter;
import controller.TaskManager;
import java.util.ArrayList;
import model.Task;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class TaskView {
    
    Inputter inputter = new Inputter();
    
    public void displayMenu(){
        System.out.println("====== Task Program ======");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
    }
    
    public Task inputTask(TaskManager tm) {
        String taskTypeID = inputter.getTaskType("Task type ID: ");
        String requirementName = inputter.getString("Requirement name: ");        
        String date = inputter.dateToString(inputter.getDate("Date: "));
        double planFrom = inputter.getDouble("From: ", "Must from 8h to 17h", 8.0, 17.0);
        double planTo = inputter.getDouble("To: ", "Must be within planFrom to 17h30", planFrom + 0.5, 17.5);
        String assignee = inputter.getString("Assignee: ");
        String reviewer = inputter.getString("Reviewer: ");

        return new Task(taskTypeID, requirementName, date, planFrom, planTo, assignee, reviewer);
    }
    
    public void displayDataTasks(ArrayList<Task> taskList) {
        System.out.printf("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Inputter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Student;

/**
 *
 * @author Admin
 */
public class View {

    public final String NOT_EMPTY_FORMAT = ".+";
    public final String YES_NO_FORMAT = "(y|n|Y|N|Yes|No)";

    public final String EMPTY_ERR = "Empty string is not allowed";
    public final String YES_NO_ERR = "Must enter yes or no";
    public final String NOT_IN_RANGE_ERR = "must greater than equal zero and less than equal ten";
    public final String NOT_NUMBER_ERR = "is digit";
    
    private final Inputter inputter = new Inputter();

    public Student inputStudent() {
        String name = inputter.getString("Name: ", NOT_EMPTY_FORMAT, EMPTY_ERR);
        String classes = inputter.getString("Classes: ", NOT_EMPTY_FORMAT, EMPTY_ERR);
        double maths = inputter.getDouble("Maths: ", 0, 10, "Maths " + NOT_IN_RANGE_ERR, "Maths " + NOT_NUMBER_ERR);
        double chemistry = inputter.getDouble("Chemistry: ", 0, 10, "Chemistry " + NOT_IN_RANGE_ERR, "Chemistry " + NOT_NUMBER_ERR);
        double physics = inputter.getDouble("Physics: ", 0, 10, "Physics " + NOT_IN_RANGE_ERR, "Physics " + NOT_NUMBER_ERR);
        return new Student(name, classes, maths, chemistry, physics);
    }
    
    public void displayStudentsInfo(ArrayList<Student> students) {
        int count = 1;        
        for (Student s : students) {
            System.out.println("------ Student " + count + " Info ------");
            System.out.println("Name: " + s.getName());
            System.out.println("Classes: " + s.getClasses());
            System.out.println("AVG: " + String.format("%.1f", s.getAverage()));
            System.out.println("Type: " + s.getType());
            System.out.println();
            count++;
        }
    }
    
    public void displayClassification(HashMap<String, Double> percentTypeStudents) {
        System.out.println("--------Classification Info -----");
        for (Map.Entry<String, Double> entry : percentTypeStudents.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }
    
}

package controller;

import common.Inputter;
import java.util.ArrayList;
import java.util.HashMap;
import model.Student;
import view.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class MarkCalculation {

    ArrayList<Student> students = new ArrayList<>();
    
    View view = new View();
    Inputter inputter = new Inputter();
    
    public void createStudent() {
        while (true) {
            Student s = view.inputStudent();
            students.add(s);
            String choice = inputter.getString("Do you want to enter more student information? (Y/N): ", view.YES_NO_FORMAT, view.YES_NO_ERR);
            if (choice.matches("(n|N|no|No)")) {
                break;
            }
        }
    }

    public ArrayList<Student> averageStudent(ArrayList<Student> students) {
        for (Student s : students) {
            s.setAverage((s.getMaths() + s.getChemistry()+ s.getPhysics()) / 3);
            if (s.getAverage() > 7.5) {
                s.setType("A");
            } else if (6 <= s.getAverage() && s.getAverage() <= 7.5) {
                s.setType("B");
            } else if (4 <= s.getAverage() && s.getAverage() < 6) {
                s.setType("C");
            } else {
                s.setType("D");
            }
        }

        return students;
    }

    public HashMap<String, Double> getPercentTypeStudent(ArrayList<Student> students) {
        HashMap<String, Double> res = new HashMap<>();
        int n = students.size(), cA = 0, cB = 0, cC = 0, cD = 0;
        for (Student s : students) {
            String type = s.getType();
            switch (type) {
                case "A":
                    cA++;
                    break;
                case "B":
                    cB++;
                    break;
                case "C":
                    cC++;
                    break;
                default:
                    cD++;
                    break;
            }
        }

        res.put("A", 100.0 * cA / n);
        res.put("B", 100.0 * cB / n);
        res.put("C", 100.0 * cC / n);
        res.put("D", 100.0 * cD / n);
        return res;
    }
    
    public void handling() {
        inputter.displayNotice("====== Management Student Program ======");        
        createStudent();
        students = averageStudent(students);   
        HashMap<String, Double> percentTypeStudents = getPercentTypeStudent(students);        
        view.displayStudentsInfo(students);
        view.displayClassification(percentTypeStudents);
    }
    
}

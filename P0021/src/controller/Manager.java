package controller;

import model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Report;
import view.View;

/**
 *
 * @author Admin
 */
public class Manager {    
    
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Report> reports = new ArrayList<>();
    public View view = new View();
    
    // Create new student
    public void createStudent(String id, String studentName, String semester, String courseName) {        
        students.add(new Student(id, studentName, semester, courseName));
    }
    
    // Find and sort
    public void findAndSort(String studentName) {
        ArrayList<Student> studentsListFindByName = studentsListFindByName(studentName);
        Collections.sort(studentsListFindByName);
        view.displayStudentsList(studentsListFindByName);
    }

    ArrayList<Student> studentsListFindByName(String studentName) {
        ArrayList<Student> res = new ArrayList<>();
        for (Student s : students) {
            if (s.getStudentName().contains(studentName)) {
                res.add(s);
            }
        }
        return res;
    }

    // Update and delete   
    public void updateOrDelete(String id) {
        ArrayList<Student> studentsListFindById = studentsListFindById(id);
        
        if (studentsListFindById.isEmpty()) {
            System.err.println("Not found");
        } else {
            System.out.println("Students list found: ");
            Student student = getStudentByListFound(studentsListFindById);
            if (Validation.getInputUD("Do you want to update (U) or delete (D) student: ")) {                
                String name = Validation.getString("Enter student name: ");                
                String semester = Validation.getString("Enter semester: ");                
                String course = Validation.getInputCourse("Enter name course: ");                
                if (checkStudentExist(this, id, name, semester, course)) {
                    // student.setStudentName(name);
                    for (Student s : studentsListFindById) {
                        s.setStudentName(name);
                    }
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.out.println("Update success.");
                }
            } else {
                students.remove(student);
                System.out.println("Delete success.");
            }
        }
    }

    Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        view.displayStudentsList(listStudentFindByName);        
        int choice = Validation.getInt("Enter student: ", 1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    public ArrayList<Student> studentsListFindById(String id) {
        ArrayList<Student> res = new ArrayList<>();
        for (Student s : students) {
            if (id.equalsIgnoreCase(s.getId())) {
                res.add(s);
            }
        }
        return res;
    }
    
    public boolean checkStudentExist(Manager m, String id, String studentName, String semester, String courseName) {
        for (Student s : students) {
            if (studentName.equalsIgnoreCase(s.getStudentName())
                    && semester.equalsIgnoreCase(s.getSemester())
                    && courseName.equalsIgnoreCase(s.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    // Check report exist
    public boolean checkReportExist(Manager m, String name, String course, int total) {
        for (Report r : reports) {
            if (name.equalsIgnoreCase(r.getStudentName())
                    && course.equalsIgnoreCase(r.getCourseName())
                    && total == r.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    // Report
    public void report() {
        reports = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (Student s : students) {
            String key = s.getStudentName() + "|" + s.getCourseName();
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            reports.add(new Report(key.split("[|]")[0], key.split("[|]")[1], entry.getValue()));
        }
    }
    
}

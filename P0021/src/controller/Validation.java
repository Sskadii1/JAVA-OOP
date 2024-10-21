package controller;

import model.Student;
import java.util.ArrayList;
import java.util.Scanner;
import model.Report;

/**
 *
 * @author Admin
 */
public class Validation {

    static Scanner sc = new Scanner(System.in);
    
    public static int getInt(String msg, int min, int max) {
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

    public static String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (!s.isEmpty()) {
                return s;
            }
            System.err.println("Empty string is not allowed");
        }
    }

    // Check user input yes/no
    public static boolean getInputYN(String msg) {
        while (true) {
            String s = getString(msg);
            if (s.equalsIgnoreCase("Y")) {
                return true;
            }
            if (s.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N");
        }
    }

    // Check user input u/d
    public static boolean getInputUD(String msg) {
        while (true) {
            String s = getString(msg);
            if (s.equalsIgnoreCase("U")) {
                return true;
            }
            if (s.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D");
        }
    }

    // Check user input course
    public static String getInputCourse(String msg) {
        while (true) {
            String s = getString(msg);
            if (s.equalsIgnoreCase("java") 
                    || s.equalsIgnoreCase(".net") 
                    || s.equalsIgnoreCase("c/c++")) {
                return s;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
        }
    }

    // Check student exist
//    public static boolean checkStudentExist(Manager m, String id, String studentName, String semester, String courseName) {
//        ArrayList<Student> students = m.students;
//        for (Student s : students) {
//            if (studentName.equalsIgnoreCase(s.getStudentName())
//                    && semester.equalsIgnoreCase(s.getSemester())
//                    && courseName.equalsIgnoreCase(s.getCourseName())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // Check report exist
//    public static boolean checkReportExist(Manager m, String name, String course, int total) {
//        ArrayList<Report> reports = m.reports;
//        for (Report r : reports) {
//            if (name.equalsIgnoreCase(r.getStudentName())
//                    && course.equalsIgnoreCase(r.getCourseName())
//                    && total == r.getTotalCourse()) {
//                return false;
//            }
//        }
//        return true;
//    }

    // Check id exist
    public static boolean checkIdExist(Manager m, String id, String name) {
        ArrayList<Student> students = m.students;        
        for (Student s : students) {
            if (id.equalsIgnoreCase(s.getId()) && !name.equalsIgnoreCase(s.getStudentName())) {
                return false;
            }
        }
        return true;
    }
}

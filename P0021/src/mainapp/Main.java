package mainapp;

import controller.Manager;
import controller.Validation;
import model.Student;


/**
 *
 * @author Admin
 */
public class Main {
    
    Manager manager = new Manager();
    
    public void execute() {
        int choice;
        do {
            manager.view.display();
            choice = Validation.getInt("Choose: ", 1, 5);
            switch (choice) {
                case 1:
                    while (manager.students.size() < 1 || Validation.getInputYN("Do you want to continue (Y/N): ")) {
                        Student student = manager.view.inputStudent(manager);
                        manager.createStudent(student.getId(), student.getStudentName(), student.getSemester(), student.getCourseName());
                    }
                    break;
                case 2:
                    if (manager.students.isEmpty()) {
                        System.err.println("Empty list");
                    } else {
                        String studentName = Validation.getString("Enter name to search: ");
                        manager.findAndSort(studentName);
                    }
                    break;
                case 3:
                    if (manager.students.isEmpty()) {
                        System.err.println("Empty list");
                    } else {
                        String id = Validation.getString("Enter id: ");     
                        manager.updateOrDelete(id);
                    }
                    break;
                case 4:                    
                    if (manager.students.isEmpty()) {
                        System.err.println("Empty list");                        
                    } else {
                        manager.report();
                        manager.view.displayReportsList(manager.reports);
                    }
                    break;
                case 5:
                    return;
            }
            
            System.out.println();
        } while (choice != 5);
    }

    public static void main(String[] args) {
        new Main().execute();
    }

}

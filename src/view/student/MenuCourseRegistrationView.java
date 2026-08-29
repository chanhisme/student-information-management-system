package view.student;

import java.util.Scanner;

import DataStructure.MyLinkedList;
import model.student.Student;
import model.subject.Subject;
import view.BaseMenuView;

public class MenuCourseRegistrationView extends BaseMenuView {

    public MenuCourseRegistrationView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== COURSE REGISTRATION ==========");
        System.out.println("1. Register Course");
        System.out.println("2. Drop Course");
        System.out.println("3. View Registered Courses");
        System.out.println("4. Undo Last Operation");
        System.out.println("5. Redo Last Operation");
        System.out.println("0. Back");
        System.out.println("==========================================");
    }

    public String inputIdSubject() {
        System.out.print("Enter id subject: ");
        return normalizeInput(scanner.nextLine().toUpperCase());
    }

    public void displayAllRegisteredCoursesOneStudent(MyLinkedList<Subject> registeredSubject) {
        System.out.println("\n=============================================");
        System.out.println("          REGISTERED COURSES");
        System.out.println("=============================================");

        if (registeredSubject.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.printf("%-12s %-30s %-10s%n", "Subject ID", "Subject Name", "Credits");
            System.out.println("---------------------------------------------");

            for (Subject subject : registeredSubject) {
                System.out.printf(
                        "%-12s %-30s %-10d%n",
                        subject.getId(),
                        subject.getName(),
                        subject.getCredits()
                );
            }
        }

        System.out.println("=============================================\n");
    }
}

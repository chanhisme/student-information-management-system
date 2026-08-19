package view;

import model.Student;
import java.util.Scanner;

public class MenuStudentManageView extends BaseMenuView {

    public MenuStudentManageView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View All Students");
        System.out.println("0. Back");
        System.out.println("========================================");
    }

    public Student inputStudentData() {
        System.out.println("\n--- Enter Student Details ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Major: ");
        String major = scanner.nextLine().trim();

        return new Student(name, id, major, null, null);
    }
}

package view;

import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        String id;
        while (true) {
            System.out.print("Enter Student ID: ");
            id = scanner.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Student ID cannot be empty.");
                continue;
            }

            break;
        }

        String name;
        while (true) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty.");
                continue;
            }
            if (!name.matches("[\\p{L} ]+")) {
                System.out.println("Student name can only contain letters and spaces.");
                continue;
            }
            break;
        }

        String major;
        while (true) {
            System.out.print("Enter Major: ");
            major = scanner.nextLine().trim();

            if (major.isEmpty()) {
                System.out.println("Major cannot be empty.");
                continue;
            }
            break;
        }

        LocalDate birth;

        while (true) {
            try {
                System.out.print("Enter Birth Date (dd/MM/yyyy): ");
                String birthInput = scanner.nextLine().trim();

                birth = LocalDate.parse(birthInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please use dd/MM/yyyy.");
            }
        }

        return new Student(name, id, major, birth, null);
    }
}

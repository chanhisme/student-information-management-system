package view.student;

import java.util.Scanner;
import view.BaseMenuView;

public class MenuStudentInformationView extends BaseMenuView {
    public MenuStudentInformationView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT INFORMATION ==========");
        System.out.println("1. View Student Profile");
        System.out.println("2. View Academic Transcript");
        System.out.println("3. View GPA");
        System.out.println("4. View Credit Summary");
        System.out.println("5. View Graduation Progress");
        System.out.println("0. Back");
        System.out.println("=========================================");
    }

    public String inputStudentId() {
        System.out.print("Enter Student ID (Q to cancel): ");
        String line = readLineOrNull();
        if (line == null) {
            return null;
        }
        return normalizeInput(line).toUpperCase();
    }
}

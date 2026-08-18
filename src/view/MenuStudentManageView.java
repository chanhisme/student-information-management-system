package view;

import java.util.Scanner;

public class MenuStudentManageView {
    private final Scanner scanner;

    public MenuStudentManageView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("========== STUDENT MANAGEMENT ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Registration");
        System.out.println("3. Student Information");
        System.out.println("4. Academic Management");
        System.out.println("5. Graduation Progress");
        System.out.println("0. Back");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
    public int inputChoice(){
        return Integer.parseInt(scanner.nextLine());
    }
    public void showInvalidInput(){
        System.err.println("Invalid choice. Please try again.");
    }
}

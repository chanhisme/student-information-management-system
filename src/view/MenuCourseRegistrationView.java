package view;

import java.util.Scanner;

public class MenuCourseRegistrationView {

    private final Scanner scanner;

    public MenuCourseRegistrationView(Scanner scanner) {
        this.scanner = scanner;
    }


    public void showMenu() {
        System.out.println("\n========== COURSE REGISTRATION ==========");
        System.out.println("1. Register Course");
        System.out.println("2. Drop Course");
        System.out.println("3. View Registered Courses");
        System.out.println("4. Undo Last Operation");
        System.out.println("5. Redo Last Operation");
        System.out.println("0. Back");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");
    }

    public int inputChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    public void showInvalidChoice() {
        System.err.println("Invalid choice. Please try again.");
    }
}

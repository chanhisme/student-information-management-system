package view;

import java.util.Scanner;

public class MenuView {

    private final Scanner scanner;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Subjects");
        System.out.println("3. Manage Faculties");
        System.out.println("0. Exit");
    }

    public int inputChoice() {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showInvalidChoice() {
        System.err.println("Invalid choice. Please try again.");
    }

    public void showExitMessage() {
        System.out.println("Goodbye!");
    }
}
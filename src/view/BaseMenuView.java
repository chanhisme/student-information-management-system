package view;

import java.util.Scanner;

public abstract class BaseMenuView {

    protected final Scanner scanner;

    public BaseMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void showMenu();

    public int inputChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                showInvalidChoice();
            }
        }
    }

    public int inputChoice(int min, int max) {
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < min || choice > max) {
                    showInvalidChoice();
                    continue;
                }
                return choice;
            } catch (NumberFormatException e) {
                showInvalidChoice();
            }
        }
    }

    public void showInvalidChoice() {
        System.err.println("Invalid choice. Please try again.");
    }
}
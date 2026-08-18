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
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                showInvalidChoice();
            }
        }
    }

    public void showInvalidChoice() {
        System.err.println("Invalid choice. Please try again.");
    }
}
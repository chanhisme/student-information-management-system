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
            System.out.print("Enter your choice (-1 to quit): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < -1) {
                    showInvalidChoice();
                    continue;
                }

                return choice;

            } catch (NumberFormatException e) {
                showInvalidChoice();
            }
        }
    }

    public int inputChoice(int min, int max) {
        while (true) {
            System.out.print("Enter your choice (-1 to quit): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice != -1 && (choice < min || choice > max)) {
                    showInvalidChoice();
                    continue;
                }
                return choice;
            } catch (NumberFormatException e) {
                showInvalidChoice();
            }
        }
    }

    public boolean isQuit(String input) {
        boolean flag = false;
        if (input != null) {
            if (input.trim().equalsIgnoreCase("Q")) {
                flag = true;
            }
        }
        return flag;
    }

    public String readLineOrNull() {
        String line = scanner.nextLine();
        String result = line;
        if (isQuit(line)) {
            result = null;
        }
        return result;
    }

    public String normalizeInput(String input) {
        return input.replaceAll("\\s+", "");
    }

    public void showInvalidChoice() {
        ConsoleColor.printError("Invalid choice. Please try again.");
    }
}   
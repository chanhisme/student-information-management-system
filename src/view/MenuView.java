package view;

import java.util.Scanner;

public class MenuView extends BaseMenuView {

    public MenuView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Subjects");
        System.out.println("3. Manage Faculties");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void showExitMessage() {
        System.out.println("Goodbye!");
    }
}
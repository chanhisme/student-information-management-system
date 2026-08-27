package view.faculty;

import model.faculty.Major;
import view.BaseMenuView;
import view.ConsoleColor;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuMajorManagementView extends BaseMenuView {

    public MenuMajorManagementView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("========== MAJOR MANAGEMENT ==========");
        System.out.println("1. View all majors");
        System.out.println("2. Add major");
        System.out.println("3. Update major");
        System.out.println("4. Delete major");
        System.out.println("5. Search major");
        System.out.println("0. Back");
    }

    public String inputAddMajor() {
        while (true) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty() && name.matches("^[\\p{L} ]+$")) {
                return name;
            }
            ConsoleColor.printError("This name already existed");
        }
    }


    public void displayAllMajors(ArrayList<Major> majors) {
        System.out.println("Majors");
        System.out.println("--------------------------------");
        for (int i = 0; i < majors.size(); i++) {
            Major major = majors.get(i);
            System.out.printf("%d. %s - %s\n", (i + 1), major.getId(), major.getName());
        }
    }
}

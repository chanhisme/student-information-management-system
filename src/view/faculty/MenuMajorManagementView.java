package view.faculty;

import model.faculty.Major;
import view.BaseMenuView;
import view.ConsoleColor;

import java.util.ArrayList;
import java.util.Locale;
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

    public String inputName() {
        while (true) {
            System.out.print("Enter new name (Q to cancel): ");
            String line = readLineOrNull();
            if (line == null) {
                return null;
            }
            String name = line.trim();
            if (!name.isEmpty() && name.matches("^[\\p{L} ]+$")) {
                return name;
            }
            ConsoleColor.printError("This name wrong format");
        }
    }

    public String inputId(){
        System.out.print("Enter id (Q to cancel): ");
        String line = readLineOrNull();
        if (line == null) {
            return null;
        }
        return line.trim().toUpperCase();
    }


    public void displayAllMajors(ArrayList<Major> majors) {
        System.out.println("Majors");
        System.out.println("--------------------------------");
        for (int i = 0; i < majors.size(); i++) {
            Major major = majors.get(i);
            System.out.printf("%d. %s - %s\n", (i + 1), major.getId(), major.getName());
        }
    }


    public int inputUpdate(Major major){
        System.out.println("============UPDATE MAJOR============");
        System.out.println("[1]. Name: " + major.getName());
        System.out.println("[0]. DONE");
        return inputChoice(0, 1);
    }

    public void displayOneMajor(Major major){
        System.out.printf("%s %s\n\n", major.getId(), major.getName());
    }
}

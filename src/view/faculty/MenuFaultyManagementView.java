package view.faculty;

import java.util.ArrayList;
import java.util.Scanner;

import model.faculty.Faculty;
import view.BaseMenuView;

public class MenuFaultyManagementView extends BaseMenuView {

    public MenuFaultyManagementView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("========== FACULTY MANAGEMENT ==========");
        System.out.println("1. View all faculties");
        System.out.println("2. Add faculty");
        System.out.println("3. Update faculty");
        System.out.println("4. Delete faculty");
        System.out.println("5. Search faculty");
        System.out.println("6. Manage majors");
        System.out.println("7. View faculty details");
        System.out.println("0. Back");
    }

    public Faculty inputFaculty() {
        String facultyName = inputFacultyName();
        String preFix = inputPreFix();
        return new Faculty(preFix, facultyName);
    }

    public String inputPreFix() {
        String preFix = null;
        while (preFix == null) {
            System.out.print("Enter id faculty: ");
            preFix = scanner.nextLine();
        }
        return preFix;
    }

    public String inputFacultyName() {
        String facultyName = null;
        while (facultyName == null) {
            System.out.print("Enter name of faculty: ");
            facultyName = scanner.nextLine();
        }
        return facultyName;
    }

    public void displayAllFaculties(ArrayList<Faculty> faculties) {
        if (faculties == null || faculties.isEmpty()) {
            System.out.println("\nNo faculty found");
            return;
        }
        System.out.println("=========FACILITIES=========");
        for (int i = 0; i < faculties.size(); i++) {
            Faculty faculty = faculties.get(i);
            System.out.printf("%d. %s - %s\n", (i + 1), faculty.getPrefix(), faculty.getName());
        }
    }
}

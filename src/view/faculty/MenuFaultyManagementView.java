package view.faculty;

import java.util.ArrayList;
import java.util.Scanner;

import model.faculty.Faculty;
import model.faculty.Major;
import view.BaseMenuView;
import view.ConsoleColor;

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
    public boolean confirmDelete(){
        System.out.println("=========CONFIRM DELETE=========");
        System.out.println("If you remove that all majors of this faculty will be removed");
        System.out.println("[1]. YES");
        System.out.println("[2]. NO");
        int choice = inputChoice(1,2);
        return choice == 1;
    }
    public Faculty inputFaculty() {
        String facultyName = inputFacultyName();
        String preFix = inputPreFix();
        return new Faculty(preFix, facultyName);
    }

    public String inputPreFix() {
        while (true) {
            System.out.print("Enter faculty id: ");
            String preFix = scanner.nextLine().trim().toUpperCase();
            if(!preFix.isEmpty() && preFix.matches("[A-Za-z]+")){
                return preFix;
            }
            ConsoleColor.printError("Invalid prefix. Use letters only.");
        }

    }

    public String inputFacultyName() {
        String facultyName = null;
        while (facultyName == null) {
            System.out.print("Enter name of faculty: ");
            facultyName = scanner.nextLine();
            if (facultyName.matches(".*\\d.*")) {
                throw new IllegalArgumentException("Faculty name must not contain numbers.");
            }
        }
        return facultyName;

    }

    public void displayAllFaculties(ArrayList<Faculty> faculties) {
        if (faculties == null || faculties.isEmpty()) {
            System.out.println("\nNo faculty found");
            return;
        }
        System.out.println("=========FACULTIES=========");
        for (int i = 0; i < faculties.size(); i++) {
            Faculty faculty = faculties.get(i);
            System.out.printf("%d. %s - %s\n", (i + 1), faculty.getPrefix(), faculty.getName());
        }
    }
    public void displayOneFaculty(Faculty faculty){
        System.out.printf("%s - %s\n", faculty.getPrefix(), faculty.getName());
    }

    public int displayUpdateMenuAndGetChoice(Faculty faculty) {
        System.out.println("\n============= UPDATE FACULTY =============");
        System.out.println("[1]. Name: " + faculty.getName());
        System.out.println("[0]. DONE");
        return inputChoice(0, 1);
    }

    public void displayDetailFaculty(Faculty faculty){
        System.out.println("\n========== VIEW FACULTY DETAILS ==========");
        System.out.println("Enter faculty id: " + faculty.getPrefix());
        System.out.println("\nFaculty Details");
        System.out.println("--------------------------------");
        System.out.println("Faculty ID   : "+faculty.getPrefix());
        System.out.println("Faculty Name : "+faculty.getName());
        System.out.println("\nMajors");
        System.out.println("--------------------------------");
        ArrayList <Major> majors = faculty.getMajors();
        for(int i = 0; i < majors.size(); i++){
            Major major = majors.get(i);
            System.out.printf("%d. %s - %s\n", (i+1), major.getId(), major.getName());
        }
        System.out.println("\nTotal majors: " + majors.size());

    }
}

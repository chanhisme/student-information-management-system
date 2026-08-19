package view;

import java.util.Scanner;

public class MenuStudentInformationView extends BaseMenuView {
    public MenuStudentInformationView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT INFORMATION ==========");
        System.out.println("1. View Student Profile");
        System.out.println("2. View Academic Transcript");
        System.out.println("3. View GPA");
        System.out.println("4. View Credit Summary");
        System.out.println("5. View Graduation Progress");
        System.out.println("0. Back");
        System.out.println("=========================================");
    }
}

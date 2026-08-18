package view;

import java.util.Scanner;

public class MenuStudentView extends BaseMenuView {

    public MenuStudentView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT MENU ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Registration");
        System.out.println("3. Student Information");
        System.out.println("4. Academic Management");
        System.out.println("5. Graduation Progress");
        System.out.println("0. Back");
        System.out.println("========================================");
    }
}

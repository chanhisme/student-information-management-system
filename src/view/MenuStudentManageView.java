package view;

import java.util.Scanner;

public class MenuStudentManageView extends BaseMenuView {

    public MenuStudentManageView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Registration");
        System.out.println("3. Student Information");
        System.out.println("4. Academic Management");
        System.out.println("5. Graduation Progress");
        System.out.println("0. Back");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
}

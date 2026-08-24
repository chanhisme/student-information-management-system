package view.faculty;

import java.util.Scanner;
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
}

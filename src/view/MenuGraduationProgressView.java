package view;

import java.util.Scanner;

public class MenuGraduationProgressView extends BaseMenuView {
    public MenuGraduationProgressView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== GRADUATION PROGRESS ==========");
        System.out.println("1. View Graduation Progress");
        System.out.println("2. Suggest Available Subjects");
        System.out.println("0. Back");
        System.out.println("=========================================");
    }
}

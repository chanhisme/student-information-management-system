package view.student;

import java.util.Scanner;
import view.BaseMenuView;

public class MenuAcademicManagementView extends BaseMenuView {

    public MenuAcademicManagementView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== ACADEMIC MANAGEMENT ==========");
        System.out.println("1. Manage Grades");
        System.out.println("2. Calculate GPA");
        System.out.println("3. Generate Academic Transcript");
        System.out.println("4. View Semester Statistics");
        System.out.println("5. Calculate Credits");
        System.out.println("0. Back");
        System.out.println("=========================================");
    }


}

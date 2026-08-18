package view;

import java.util.Scanner;

public class MenuCourseRegistrationView extends BaseMenuView {

    public MenuCourseRegistrationView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== COURSE REGISTRATION ==========");
        System.out.println("1. Register Course");
        System.out.println("2. Drop Course");
        System.out.println("3. View Registered Courses");
        System.out.println("4. Undo Last Operation");
        System.out.println("5. Redo Last Operation");
        System.out.println("0. Back");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");
    }
}

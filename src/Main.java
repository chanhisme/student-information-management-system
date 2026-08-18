import controller.*;
import view.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        StudentController studentController = new StudentController(menuStudentView);
        MenuController menuController = new MenuController(menuView, studentController);

        menuController.run();
    }
}
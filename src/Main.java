import controller.MenuController;
import view.MenuView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuView menuView = new MenuView(scanner);
        MenuController menuController = new MenuController(menuView);

        menuController.run();
    }
}
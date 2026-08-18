package controller;

import view.MenuView;

public class MenuController {

    private final MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
    }

    public void run() {
        menuView.showMainMenu();
    }
}
package controller.student;

import view.student.MenuGraduationProgressView;

public class GraduationProgressController {
    private final MenuGraduationProgressView menuGraduationProgressView;

    public GraduationProgressController(MenuGraduationProgressView menuGraduationProgressView) {
        this.menuGraduationProgressView = menuGraduationProgressView;
    }

    public void run() {
        while (true) {
            menuGraduationProgressView.showMenu();
            int choice = menuGraduationProgressView.inputChoice(0,2);
            if (choice == -1) {
                return;
            }
            switch (choice) {
                case 0:
                    return;
            }
        }
    }
}

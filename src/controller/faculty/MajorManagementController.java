package controller.faculty;

import model.faculty.Faculty;
import service.faculty.FacultyService;
import view.faculty.MenuMajorManagementView;

public class MajorManagementController {
    private final MenuMajorManagementView menuMajorManagementView;
    private final FacultyService facultyService;

    public MajorManagementController(MenuMajorManagementView menuMajorManagementView,
                                     FacultyService facultyService) {

        this.menuMajorManagementView = menuMajorManagementView;
        this.facultyService = facultyService;
    }

    public void run(Faculty faculty){
        while(true){
            menuMajorManagementView.showMenu();
            int choice = menuMajorManagementView.inputChoice(0, 5);
            switch (choice){

                case 1:
                    menuMajorManagementView.displayAllMajors(faculty.getMajors());
                    break;

                case 0:
                    return;
            }
        }
    }
}


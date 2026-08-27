package controller.faculty;

import model.faculty.Faculty;
import model.faculty.Major;
import service.faculty.FacultyService;
import view.ConsoleColor;
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
                case 2:
                    Major major = new Major(
                            facultyService.generateMajorId(faculty),
                            menuMajorManagementView.inputAddMajor());
                    try{
                        facultyService.addMajor(major, faculty.getPrefix());
                        ConsoleColor.printSuccess("Add new major successfully");
                        ConsoleColor.printSuccess("Save new major successfully");

                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 4:
                    String id = menuMajorManagementView.inputId();
                    try{
                        facultyService.deleteMajor(id, faculty.getPrefix());
                        ConsoleColor.printSuccess("Delete successfully");
                        ConsoleColor.printSuccess("Save successfully");
                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}


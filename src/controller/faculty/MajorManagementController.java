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

    public void run(Faculty faculty) {
        while (true) {
            menuMajorManagementView.showMenu();
            int choice = menuMajorManagementView.inputChoice(0, 5);
            Major major;
            String Majorid;
            switch (choice) {

                case 1:
                    menuMajorManagementView.displayAllMajors(faculty.getMajors());
                    break;
                case 2:
                    major = new Major(
                            facultyService.generateMajorId(faculty),
                            menuMajorManagementView.inputName());
                    try {
                        facultyService.addMajor(major, faculty.getPrefix());
                        ConsoleColor.printSuccess("Add new major successfully");
                        ConsoleColor.printSuccess("Save new major successfully");

                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 3:
                    major = facultyService.findMajorById(menuMajorManagementView.inputId(), faculty.getPrefix());
                    if (major == null) {
                        ConsoleColor.printError("This major is not existed");
                        break;
                    }
                    handleUpdate(major, faculty.getPrefix());
                    break;
                case 4:
                    Majorid = menuMajorManagementView.inputId();
                    try {
                        facultyService.deleteMajor(Majorid, faculty.getPrefix());
                        ConsoleColor.printSuccess("Delete successfully");
                        ConsoleColor.printSuccess("Save successfully");
                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 5:
                    Majorid = menuMajorManagementView.inputId();
                    major = facultyService.findMajorById(Majorid, faculty.getPrefix());
                    if (major == null) {
                        ConsoleColor.printError("This major is not existed");
                        break;
                    }
                    menuMajorManagementView.displayOneMajor(major);
                    break;
                case 0:
                    return;
            }
        }
    }


    private void handleUpdate(Major major, String preFix) {
        boolean updating = true;
        while (updating) {
            int choice = menuMajorManagementView.inputUpdate(major);
            switch (choice) {
                case 1:
                    String newName = menuMajorManagementView.inputName();
                    major.setName(newName);
                    ConsoleColor.printSuccess("Changed name successfully");
                    break;
                case 0:
                    updating = false;
                    break;
            }
            if (choice != 0) {
                try {
                    facultyService.updateMajor(major, preFix);
                    ConsoleColor.printSuccess("major saved successfully!");
                } catch (IllegalArgumentException e) {
                    ConsoleColor.printError(e.getMessage());
                }
            }
        }
    }
}


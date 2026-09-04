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
            if (choice == -1) {
                return;
            }
            switch (choice) {
                case 1:
                    viewAllMajors(faculty);
                    break;
                case 2:
                    addMajor(faculty);
                    break;
                case 3:
                    updateMajorById(faculty);
                    break;
                case 4:
                    deleteMajorById(faculty);
                    break;
                case 5:
                    viewMajorById(faculty);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void viewAllMajors(Faculty faculty) {
        menuMajorManagementView.displayAllMajors(faculty.getMajors());
    }

    private void addMajor(Faculty faculty) {
        String name = menuMajorManagementView.inputName();
        if (name == null) {
            return;
        }
        Major major = new Major(
                facultyService.generateMajorId(faculty),
                name);
        try {
            facultyService.addMajor(major, faculty.getPrefix());
            ConsoleColor.printSuccess("Add new major successfully");
            ConsoleColor.printSuccess("Save new major successfully");
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void updateMajorById(Faculty faculty) {
        try {
            String majorId = menuMajorManagementView.inputId();
            if (majorId == null) {
                return;
            }
            Major major = findMajorOrThrow(majorId, faculty.getPrefix());
            handleUpdate(major, faculty.getPrefix());
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void deleteMajorById(Faculty faculty) {
        String majorId = menuMajorManagementView.inputId();
        if (majorId == null) {
            return;
        }
        try {
            facultyService.deleteMajor(majorId, faculty.getPrefix());
            ConsoleColor.printSuccess("Delete successfully");
            ConsoleColor.printSuccess("Save successfully");
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void viewMajorById(Faculty faculty) {
        try {
            String majorId = menuMajorManagementView.inputId();
            if (majorId == null) {
                return;
            }
            Major major = findMajorOrThrow(majorId, faculty.getPrefix());
            menuMajorManagementView.displayOneMajor(major);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private Major findMajorOrThrow(String majorId, String preFix) {
        Major major = facultyService.findMajorById(majorId, preFix);
        if (major == null) {
            throw new RuntimeException("This major does not exist");
        }
        return major;
    }

    private void handleUpdate(Major major, String preFix) {
        boolean updating = true;
        while (updating) {
            int choice = menuMajorManagementView.inputUpdate(major);
            if (choice == -1) {
                return;
            }
            switch (choice) {
                case 1:
                    String newName = menuMajorManagementView.inputName();
                    if (newName == null) {
                        break;
                    }
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

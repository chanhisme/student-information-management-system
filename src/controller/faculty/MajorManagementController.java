package controller.faculty;

import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
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
                    if(major == null){
                        System.out.println("This major is not existed");
                        break;
                    }
                    handleUpdate(major);
                    break;
                case 4:
                    String id = menuMajorManagementView.inputId();
                    try {
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


    private void handleUpdate(Major major) {
        boolean updating = true;
        while (updating) {
            int choice = menuMajorManagementView.inputUpdate(major);
            switch (choice) {
                case 1:
                    String newName = menuMajorManagementView.inputName();
                    major.setName(newName);
                    break;
                case 0:
                    updating = false;
                    break;
            }
        }
    }
}


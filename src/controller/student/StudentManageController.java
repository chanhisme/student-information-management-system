package controller.student;

import model.faculty.Faculty;
import model.faculty.Major;
import model.student.Student;
import service.student.StudentService;
import view.ConsoleColor;
import view.student.MenuStudentManageView;

public class StudentManageController {
    private final MenuStudentManageView menuStudentManageView;
    private final StudentService studentService;

    public StudentManageController(MenuStudentManageView menuStudentManageView, StudentService studentService) {
        this.menuStudentManageView = menuStudentManageView;
        this.studentService = studentService;
    }

    public void run() {
        while (true) {
            menuStudentManageView.showMenu();
            int choice = menuStudentManageView.inputChoice(0, 5);
            String id;
            Student student;
            switch (choice) {

                case 1:
                    student = menuStudentManageView.inputStudentData();
                    if (student == null) {
                        ConsoleColor.printError("Student creation cancelled.");
                        break;
                    }
                    try {
                        studentService.addStudent(student);
                        ConsoleColor.printSuccess("Student added successfully!");
                        ConsoleColor.printSuccess("Student saved successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 2:
                    id = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("This student not existed");
                        break;
                    }
                    handleUpdateStudent(student);
                    break;
                case 3:
                    id = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("This student not existed");
                        break;
                    }
                    menuStudentManageView.displayOneStudent(student);
                    if (menuStudentManageView.confirmDelete()) {
                        try {
                            studentService.deleteStudent(id);
                            ConsoleColor.printSuccess("Student deleted successfully!");
                        } catch (IllegalArgumentException e) {
                            ConsoleColor.printError(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    menuStudentManageView.displayAllStudents(studentService.getAllStudents());
                    break;
                case 5:
                    id = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                    } else {
                        menuStudentManageView.displayOneStudent(student);
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    private void handleUpdateStudent(Student student) {
        boolean updating = true;
        while (updating) {
            int choice = menuStudentManageView.displayUpdateMenuAndGetChoice(student);

            switch (choice) {
                case 1:
                    String newName = menuStudentManageView.inputName();
                    student.setName(newName);
                    ConsoleColor.printSuccess("Updated name successfully.");
                    break;
                case 2:
                    java.time.LocalDate newBirth = menuStudentManageView.inputBirthDate();
                    student.setBirth(newBirth);
                    ConsoleColor.printSuccess("Updated date of birth successfully.");
                    break;
                case 3:
                    Faculty newFaculty = menuStudentManageView.inputFaculty();
                    if (newFaculty != null) {
                        Major newMajor = menuStudentManageView.inputMajor(newFaculty.getMajors());
                        if (newMajor != null) {
                            student.setFaculty(newFaculty);
                            student.setMajor(newMajor);
                            ConsoleColor.printSuccess("Updated faculty and major successfully.");
                        } else {
                            ConsoleColor.printError("Major selection cancelled. Faculty was not changed.");
                        }
                    }
                    break;
                case 4:
                    if (student.getFaculty() == null) {
                        ConsoleColor.printError("Student has no faculty assigned. Please select faculty first.");
                    } else {
                        Major newMajor = menuStudentManageView.inputMajor(student.getFaculty().getMajors());
                        if (newMajor != null) {
                            student.setMajor(newMajor);
                            ConsoleColor.printSuccess("Updated major successfully.");
                        }
                    }
                    break;
                case 5:
                    Student.StudentStatus status = menuStudentManageView.inputStatus();
                    if (status != null) {
                        student.setStatus(status);
                        ConsoleColor.printSuccess("Updated status successfully.");
                    }
                    break;
                case 0:
                    updating = false;
                    break;
            }

            if (choice != 0) {
                try {
                    studentService.updateStudent(student);
                    ConsoleColor.printSuccess("Student saved successfully!");
                } catch (IllegalArgumentException e) {
                    ConsoleColor.printError(e.getMessage());
                }
            }
        }
    }
}

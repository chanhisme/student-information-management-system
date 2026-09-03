package controller.student;

import model.faculty.Faculty;
import model.faculty.Major;
import model.student.Student;
import service.student.StudentService;
import view.ConsoleColor;
import view.student.MenuStudentManageView;

import java.util.ArrayList;

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
            int choice = menuStudentManageView.inputChoice(0, 6);
            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudentById(); break;
                case 3: deleteStudentById(); break;
                case 4: viewAllStudents(); break;
                case 5: searchStudents(); break;
                case 6: sortStudents(); break;
                case 0: return;
            }
        }
    }

    private void addStudent() {
        Student student = menuStudentManageView.inputStudentData();
        if (student == null) {
            ConsoleColor.printError("Student creation cancelled.");
            return;
        }
        try {
            studentService.addStudent(student);
            ConsoleColor.printSuccess("Student added successfully!");
            ConsoleColor.printSuccess("Student saved successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void updateStudentById() {
        try {
            Student student = findStudentOrThrow(menuStudentManageView.inputIdStudent());
            handleUpdateStudent(student);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void deleteStudentById() {
        String id = menuStudentManageView.inputIdStudent();
        try {
            Student student = findStudentOrThrow(id);
            menuStudentManageView.displayOneStudent(student);
            if (!menuStudentManageView.confirmDelete()) {
                return;
            }
            try {
                studentService.deleteStudent(id);
                ConsoleColor.printSuccess("Student deleted successfully!");
            } catch (IllegalArgumentException e) {
                ConsoleColor.printError(e.getMessage());
            }
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void viewAllStudents() {
        menuStudentManageView.displayAllStudents(
                new ArrayList<>(studentService.getAllStudents().values())
        );
    }

    private void searchStudents() {
        int searchingChoice = menuStudentManageView.inputSearchingChoice();
        if (searchingChoice == 1) {
            searchStudentById();
        } else {
            searchStudentsByName();
        }
    }

    private void searchStudentById() {
        String id = menuStudentManageView.inputIdStudent();
        Student student = studentService.findById(id);
        menuStudentManageView.displayOneStudent(student);
    }

    private void searchStudentsByName() {
        String name = menuStudentManageView.inputName();
        ArrayList<Student> students = new ArrayList<>(studentService.findStudentByName(name));
        menuStudentManageView.displayAllStudents(students);
    }

    private void sortStudents() {
        int sortChoice = menuStudentManageView.displaySortMenuAndGetChoice();
        if (sortChoice == 1) {
            java.util.List<Student> sorted = studentService.getStudentsSorted(
                    (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
            menuStudentManageView.displayAllStudentsSorted(sorted);
        } else if (sortChoice == 2) {
            java.util.List<Student> sorted = studentService.getStudentsSorted(
                    (s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
            menuStudentManageView.displayAllStudentsSorted(sorted);
        }
    }

    private Student findStudentOrThrow(String id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new RuntimeException("This student does not exist");
        }
        return student;
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
                        student.setFaculty(newFaculty);
                        ConsoleColor.printSuccess("Updated faculty successfully.");
                    } else {
                        ConsoleColor.printError("Faculty was not changed.");
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

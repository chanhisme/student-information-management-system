package controller.student;

import model.student.Student;
import model.subject.Subject;
import service.student.CourseRegistrationService;
import service.student.StudentService;
import service.subject.SubjectService;
import view.ConsoleColor;
import view.student.MenuCourseRegistrationView;

public class CourseRegistrationController {
    private final MenuCourseRegistrationView menuCourseRegistrationView;
    private final CourseRegistrationService courseRegistrationService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public CourseRegistrationController(MenuCourseRegistrationView menuCourseRegistrationView,
                                        CourseRegistrationService courseRegistrationService,
                                        StudentService studentService,
                                        SubjectService subjectService) {
        this.menuCourseRegistrationView = menuCourseRegistrationView;
        this.courseRegistrationService = courseRegistrationService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            menuCourseRegistrationView.showMenu();
            int choice = menuCourseRegistrationView.inputChoice(0, 5);
            String studentId;
            String subjectId;
            Student student;
            Subject subject;

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine().trim();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }

                    System.out.print("Enter Subject ID: ");
                    subjectId = scanner.nextLine().trim();
                    subject = subjectService.findById(subjectId);
                    if (subject == null) {
                        ConsoleColor.printError("Subject not found.");
                        break;
                    }

                    try {
                        courseRegistrationService.registerCourse(student, subject);
                        ConsoleColor.printSuccess("Course registered successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine().trim();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }

                    System.out.print("Enter Subject ID: ");
                    subjectId = scanner.nextLine().trim();
                    subject = subjectService.findById(subjectId);
                    if (subject == null) {
                        ConsoleColor.printError("Subject not found.");
                        break;
                    }

                    try {
                        courseRegistrationService.dropCourse(student, subject);
                        ConsoleColor.printSuccess("Course dropped successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine().trim();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }

                    java.util.List<Subject> subjects = student.getRegisteredSubjects();
                    if (subjects.isEmpty()) {
                        System.out.println("No registered courses found for this student.");
                    } else {
                        System.out.println("\n--- Registered Courses ---");
                        for (Subject s : subjects) {
                            System.out.println("- " + s.getId() + ": " + s.getName() + " (" + s.getCredits() + " credits)");
                        }
                    }
                    break;

                case 4:
                    if (courseRegistrationService.undo()) {
                        ConsoleColor.printSuccess("Undo successful!");
                    } else {
                        ConsoleColor.printError("Nothing to undo.");
                    }
                    break;

                case 5:
                    if (courseRegistrationService.redo()) {
                        ConsoleColor.printSuccess("Redo successful!");
                    } else {
                        ConsoleColor.printError("Nothing to redo.");
                    }
                    break;

                case 0:
                    return;


package controller.student;

import model.student.Student;
import model.subject.Subject;
import service.academic.RegistrationService;
import service.student.StudentService;
import service.subject.SubjectService;
import view.ConsoleColor;
import view.student.MenuCourseRegistrationView;
import view.student.MenuStudentManageView;

public class CourseRegistrationController {
    private final MenuCourseRegistrationView menuCourseRegistrationView;
    private final RegistrationService courseRegistrationService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final MenuStudentManageView menuStudentManageView;

    public CourseRegistrationController(MenuCourseRegistrationView menuCourseRegistrationView,
                                        RegistrationService courseRegistrationService,
                                        StudentService studentService,
                                        SubjectService subjectService,
                                        MenuStudentManageView menuStudentManageView) {

        this.menuCourseRegistrationView = menuCourseRegistrationView;
        this.courseRegistrationService = courseRegistrationService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.menuStudentManageView = menuStudentManageView;
    }

    public void run() {
        while (true) {
            menuCourseRegistrationView.showMenu();
            int choice = menuCourseRegistrationView.inputChoice(0, 5);
            String studentId;
            String subjectId;
            Student student;
            Subject subject;

            switch (choice) {
                case 1:
                    studentId = menuStudentManageView.inputIdStudent();
                    subjectId = menuCourseRegistrationView.inputIdSubject();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }
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
                    studentId = menuStudentManageView.inputIdStudent();
                    subjectId = menuCourseRegistrationView.inputIdSubject();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }
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
                    studentId = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(studentId);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                        break;
                    }
                    menuCourseRegistrationView.displayAllRegisteredCoursesOneStudent(student.getRegisteredSubjects());
                    break;

                case 4:
                    if (courseRegistrationService.undo()) {
                        ConsoleColor.printSuccess("Undo successful.");
                    } else {
                        ConsoleColor.printError("Nothing to undo.");
                    }
                    break;

                case 5:
                    if (courseRegistrationService.redo()) {
                        ConsoleColor.printSuccess("Redo successful.");
                    } else {
                        ConsoleColor.printError("Nothing to redo.");
                    }
                    break;

                case 0:
                    return;

            }
        }
    }
}
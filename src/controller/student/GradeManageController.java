package controller.student;

import model.student.Student;
import model.subject.Subject;
import service.student.StudentService;
import service.student.AcademicService;
import service.subject.SubjectService;
import view.student.MenuGradeManageView;
import view.student.MenuStudentManageView;
import view.subject.MenuSubjectManageView;
import view.ConsoleColor;

public class GradeManageController {
    private final MenuGradeManageView menuGradeManageView;
    private final MenuStudentManageView menuStudentManageView;
    private final MenuSubjectManageView menuSubjectManageView;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final AcademicService academicService;

    public GradeManageController(MenuGradeManageView menuGradeManageView, MenuStudentManageView menuStudentManageView, MenuSubjectManageView menuSubjectManageView, SubjectService subjectService, StudentService studentService, AcademicService academicService) {
        this.menuGradeManageView = menuGradeManageView;
        this.menuStudentManageView = menuStudentManageView;
        this.menuSubjectManageView = menuSubjectManageView;
        this.subjectService = subjectService;
        this.studentService = studentService;
        this.academicService = academicService;
    }


    public void run() {
        while (true) {
            menuGradeManageView.showMenu();
            int choice = menuGradeManageView.inputChoice(0, 4);

            switch (choice) {
                case 1:
                    addGrade();
                    break;
                case 2:
                    updateGrade();
                    break;
                case 3:
                    deleteGrade();
                    break;
                case 4:
                    viewGrades();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void addGrade() {
        String studentId = menuStudentManageView.inputIdStudent();
        Student student = studentService.findById(studentId);
        if (student == null) {
            ConsoleColor.printError("Student not found.");
            return;
        }

        String subjectId = menuSubjectManageView.inputId();
        Subject subject = subjectService.findById(subjectId);
        if (subject == null) {
            ConsoleColor.printError("Subject not found.");
            return;
        }

        if (!student.getRegisteredSubjects().contains(subject)) {
            ConsoleColor.printError("Student must register for the subject before receiving a grade.");
            return;
        }

        double score = menuGradeManageView.inputGrade();
        try {
            academicService.addGrade(student, subject, score);
            studentService.updateStudent(student);
            ConsoleColor.printSuccess("Grade added successfully.");
        } catch (Exception e) {
            ConsoleColor.printError("Error adding grade: " + e.getMessage());
        }
    }

    public void deleteGrade() {
        String studentId = menuStudentManageView.inputIdStudent();
        Student student = studentService.findById(studentId);
        if (student == null) {
            ConsoleColor.printError("Student not found.");
            return;
        }

        String subjectId = menuSubjectManageView.inputId();
        Subject subject = subjectService.findById(subjectId);
        if (subject == null) {
            ConsoleColor.printError("Subject not found.");
            return;
        }

        if (!student.getRegisteredSubjects().contains(subject)) {
            ConsoleColor.printError("Student must register for the subject before receiving a grade.");
            return;
        }
        try {
            academicService.deleteGrade(student, subject);
            studentService.updateStudent(student);
            ConsoleColor.printSuccess("Grade deleted successfully.");
        } catch (Exception e) {
            ConsoleColor.printError("Error deleting grade: " + e.getMessage());
        }
    }

    public void updateGrade() {
        String studentId = menuStudentManageView.inputIdStudent();
        Student student = studentService.findById(studentId);
        if (student == null) {
            ConsoleColor.printError("Student not found.");
            return;
        }

        String subjectId = menuSubjectManageView.inputId();
        Subject subject = subjectService.findById(subjectId);
        if (subject == null) {
            ConsoleColor.printError("Subject not found.");
            return;
        }

        Double currentScore = student.getSubjectGrades().get(subject.getId());
        if (currentScore == null) {
            ConsoleColor.printError("No grade exists for this subject. Use add instead.");
            return;
        }

        menuGradeManageView.displayCurrentGrade(currentScore);
        double score = menuGradeManageView.inputGrade();
        try {
            academicService.updateGrade(student, subject, score);
            studentService.updateStudent(student);
            ConsoleColor.printSuccess("Grade updated successfully.");
        } catch (Exception e) {
            ConsoleColor.printError("Error updating grade: " + e.getMessage());
        }
    }

    public void viewGrades() {
        String studentId = menuStudentManageView.inputIdStudent();
        Student student = studentService.findById(studentId);
        if (student == null) {
            ConsoleColor.printError("Student not found.");
            return;
        }
        System.out.println("--- Grades for " + student.getName() + " (" + student.getId() + ") ---");
        if (student.getSubjectGrades().isEmpty()) {
            System.out.println("No grades recorded.");
            return;
        }
        student.getSubjectGrades().forEach((subjectId, score) -> {
            Subject subject = subjectService.findById(subjectId);
            String subjectName = (subject != null) ? subject.getName() : "Unknown Subject";
            System.out.println(subjectId + " - " + subjectName + ": " + score);
        });
    }
}

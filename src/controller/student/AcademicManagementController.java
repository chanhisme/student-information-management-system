package controller.student;

import model.student.Student;
import model.subject.Subject;
import service.academic.GradeService;
import service.student.StudentService;
import service.subject.SubjectService;
import view.ConsoleColor;
import view.student.MenuAcademicManagementView;
import view.student.MenuStudentManageView;

import java.util.List;
import java.util.Map;

public class AcademicManagementController {
    private final MenuAcademicManagementView menuAcademicManagementView;
    private final GradeManageController gradeManageController;
    private final MenuStudentManageView menuStudentManageView;
    private final StudentService studentService;
    private final GradeService academicService;
    private final SubjectService subjectService;

    public AcademicManagementController(MenuAcademicManagementView menuAcademicManagementView, GradeManageController gradeManageController, MenuStudentManageView menuStudentManageView, StudentService studentService, GradeService academicService, SubjectService subjectService) {
        this.menuAcademicManagementView = menuAcademicManagementView;
        this.gradeManageController = gradeManageController;
        this.menuStudentManageView = menuStudentManageView;
        this.studentService = studentService;
        this.academicService = academicService;
        this.subjectService = subjectService;
    }

    public void run() {
        while (true) {
            menuAcademicManagementView.showMenu();
            int choice = menuAcademicManagementView.inputChoice(0, 5);
            if (choice == -1) {
                return;
            }
            switch (choice) {
                case 1:
                    gradeManageController.run();
                    break;
                case 2:
                    calculateGpa();
                    break;
                case 3:
                    generateAcademicTransciprt();
                    break;
                case 4:
                    viewSemesterStatistics();
                    break;
                case 5:
                    caculateCreadit();
                    break;
                case 0:
                    return;
            }
        }
    }

    private Student findStudent() {

        while (true) {
            String studentId = menuStudentManageView.inputIdStudent();
            if (studentId == null) {
                return null;
            }
            Student student = studentService.findById(studentId);
            if (student != null) {
                return student;
            }
            ConsoleColor.printError("This student does not exist");
        }
    }

    private void calculateGpa() {
        try {
            Student student = findStudent();
            if (student == null) {
                return;
            }
            academicService.calculateGpa(student);
            ConsoleColor.printSuccess("Calculate successfully\n");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Current GPA: " + student.getGpa());
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void generateAcademicTransciprt() {
        try {
            System.out.println("========== ACADEMIC TRANSCRIPT ==========\n");

            Student student = findStudent();
            if (student == null) {
                return;
            }
            menuStudentManageView.displayOneStudent(student);

            System.out.println("--------------- TRANSCRIPT ---------------\n");

            Map<String, Double> subjectGrades = student.getSubjectGrades();

            if (subjectGrades.isEmpty()) {
                System.out.println("No academic records found.");
                return;
            }

            System.out.printf("%-12s %-30s %-10s %-10s%n",
                    "Subject ID", "Subject Name", "Credit", "Grade");

            System.out.println("--------------------------------------------------------------");

            int totalCredits = 0;

            for (Map.Entry<String, Double> entry : subjectGrades.entrySet()) {
                String subjectId = entry.getKey();
                double grade = entry.getValue();

                Subject subject = subjectService.findById(subjectId);

                if (subject == null) {
                    continue;
                }

                int credits = subject.getCredits();
                totalCredits += credits;

                System.out.printf("%-12s %-30s %-10d %-10.2f%n",
                        subject.getId(),
                        subject.getName(),
                        credits,
                        grade);
            }

            System.out.println("--------------------------------------------------------------");

            academicService.calculateGpa(student);
            double gpa = student.getGpa();

            System.out.printf("Total Credits : %d%n", totalCredits);
            System.out.printf("GPA           : %.2f%n", gpa);

            System.out.println("==========================================");

        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }


    private void viewSemesterStatistics (){
        List<Student> students = studentService.getStudents();
        System.out.println("========== SEMESTER STATISTICS ==========");
        System.out.println("Total Students : " + studentService.getNumberOfStudents());
        System.out.println("Average GPA    : " + academicService.calculateAverageGpa(students)[0]);
        System.out.println("Highest GPA    : " + academicService.calculateAverageGpa(students)[1]);
        System.out.println("Lowest GPA     : " + academicService.calculateAverageGpa(students)[2]);
        System.out.println("==========================================");
    }

    private void caculateCreadit(){
        try{
            double passedScore = 5.0;
            int numberPassedCredit = 0, requiredCredits = 145;

            Student student = findStudent();
            Map <String, Subject> subjectMap = subjectService.getSubjectsMap();
            for(Map.Entry<String, Double> entry : student.getSubjectGrades().entrySet()){
                double score = entry.getValue();
                String subjectId = entry.getKey();
                if(score >= passedScore){
                    numberPassedCredit += subjectMap.get(subjectId).getCredits();
                }
            }
            System.out.println("\n--- Caculate credits ---");
            menuStudentManageView.displayOneStudent(student);
            System.out.println("Passed credits: " + numberPassedCredit);
            System.out.println("Remained credits: " + (requiredCredits - numberPassedCredit));

        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

}

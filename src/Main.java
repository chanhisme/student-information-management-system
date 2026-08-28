import controller.MainMenuController;
import controller.student.*;
import controller.faculty.*;
import controller.subject.SubjectManageController;
import model.student.Student;
import repository.faculty.FacultyRepository;
import repository.student.StudentRepository;
import repository.subject.SubjectRepository;
import service.faculty.FacultyService;
import service.student.StudentService;
import service.subject.SubjectService;
import view.MenuView;
import view.student.*;
import view.faculty.*;
import view.subject.MenuSubjectManageView;

import java.lang.management.ManagementPermission;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Student> students = new TreeMap<>();
        StudentRepository studentRepository = new StudentRepository(students);
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.load();
        studentRepository.load(facultyRepository);


        // 1. Initialize Views
        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        MenuStudentManageView menuStudentManageView = new MenuStudentManageView(scanner, facultyRepository.getAll());
        MenuCourseRegistrationView menuCourseRegistrationView = new MenuCourseRegistrationView(scanner);
        MenuStudentInformationView menuStudentInformationView = new MenuStudentInformationView(scanner);
        MenuAcademicManagementView menuAcademicManagementView = new MenuAcademicManagementView(scanner);
        MenuGraduationProgressView menuGraduationProgressView = new MenuGraduationProgressView(scanner);
        MenuFaultyManagementView menuFaultyManagementView = new MenuFaultyManagementView(scanner);
        MenuMajorManagementView menuMajorManagementView = new MenuMajorManagementView(scanner);
        MenuSubjectManageView menuSubjectManageView = new MenuSubjectManageView(scanner);

        // 2. Initialize Repositories & Services
        StudentService studentService = new StudentService(studentRepository);
        FacultyService facultyService = new FacultyService(facultyRepository);

        SubjectRepository subjectRepository = new SubjectRepository();
        subjectRepository.load();
        SubjectService subjectService = new SubjectService(subjectRepository);

        // 3. Initialize Sub-Controllers
        StudentManageController studentManageController = new
                StudentManageController(menuStudentManageView, studentService);

        CourseRegistrationController courseRegistrationController =
                new CourseRegistrationController(menuCourseRegistrationView);

        StudentInformationViewController studentInformationViewController =
                new StudentInformationViewController(menuStudentInformationView);

        AcademicManagementController academicManagementController =
                new AcademicManagementController(menuAcademicManagementView);

        GraduationProgressController graduationProgressController =
                new GraduationProgressController(menuGraduationProgressView);

        MajorManagementController majorManagementController =
                new MajorManagementController(menuMajorManagementView, facultyService);

        SubjectManageController subjectManageController =
                new SubjectManageController(menuSubjectManageView, subjectService);

        // 3. Initialize Feature Controllers
        StudentController studentController = new StudentController(
                menuStudentView,
                studentManageController,
                courseRegistrationController,
                studentInformationViewController,
                academicManagementController,
                graduationProgressController
        );

        FaultyManagementController faultyManagementController = new FaultyManagementController(
                menuFaultyManagementView,
                facultyService,
                majorManagementController
        );


        //Initialize Main Menu Controller & Run
        MainMenuController mainMenuController = new MainMenuController(
                menuView,
                studentController,
                subjectManageController,
                faultyManagementController);


        mainMenuController.run();
        studentRepository.save();
        facultyRepository.save();
        subjectRepository.save();
    }
}
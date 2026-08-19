import controller.*;
import model.Student;
import repository.StudentRepository;
import service.StudentService;
import view.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Student> students = new TreeMap<>();
        StudentRepository studentRepository = new StudentRepository(students);


        // 1. Initialize Views
        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        MenuStudentManageView menuStudentManageView = new MenuStudentManageView(scanner);
        MenuCourseRegistrationView menuCourseRegistrationView = new MenuCourseRegistrationView(scanner);
        MenuStudentInformationView menuStudentInformationView = new MenuStudentInformationView(scanner);
        MenuAcademicManagementView menuAcademicManagementView = new MenuAcademicManagementView(scanner);
        MenuGraduationProgressView menuGraduationProgressView = new MenuGraduationProgressView(scanner);

        // 2. Initialize Repositories & Services
        StudentService studentService = new StudentService(studentRepository);

        // 3. Initialize Sub-Controllers
        StudentManageController studentManageController = new StudentManageController(menuStudentManageView, studentService);
        CourseRegistrationController courseRegistrationController = new CourseRegistrationController(menuCourseRegistrationView);
        StudentInformationViewController studentInformationViewController = new StudentInformationViewController(menuStudentInformationView);
        AcademicManagementController academicManagementController = new AcademicManagementController(menuAcademicManagementView);
        GraduationProgressController graduationProgressController = new GraduationProgressController(menuGraduationProgressView);

        // 3. Initialize Feature Controllers
        StudentController studentController = new StudentController(
                menuStudentView,
                studentManageController,
                courseRegistrationController,
                studentInformationViewController,
                academicManagementController,
                graduationProgressController
        );







        //Initialize Main Menu Controller & Run
        MainMenuController mainMenuController = new MainMenuController(menuView, studentController);
        mainMenuController.run();
    }
}
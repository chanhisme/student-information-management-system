import controller.MainMenuController;
import controller.student.*;
import controller.faculty.*;
import controller.subject.SubjectManageController;
import model.faculty.Faculty;
import model.student.Student;
import model.subject.Subject;
import repository.academic.GradeRepository;
import repository.academic.RegistrationRepository;
import repository.faculty.FacultyRepository;
import repository.student.StudentRepository;
import repository.subject.SubjectRepository;
import service.faculty.FacultyService;
import service.student.StudentService;
import service.academic.RegistrationService;
import service.academic.GradeService;
import service.subject.SubjectService;
import view.MenuView;
import view.student.*;
import view.faculty.*;
import view.subject.MenuSubjectManageView;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Student> students = new TreeMap<>();
        Map<String, Faculty> facultyMap = new LinkedHashMap<>();
        Map<String, Subject> subjectMap = new LinkedHashMap<>();

        StudentRepository studentRepository = new StudentRepository(students);
        FacultyRepository facultyRepository = new FacultyRepository(facultyMap);
        SubjectRepository subjectRepository = new SubjectRepository(subjectMap);
        GradeRepository gradeRepository = new GradeRepository(students);
        RegistrationRepository registrationRepository = new RegistrationRepository(
                students,
                subjectMap);

        facultyRepository.load();
        studentRepository.load(facultyRepository);
        subjectRepository.load();
        registrationRepository.load();
        gradeRepository.load();


        // 1. Initialize Views
        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        MenuStudentManageView menuStudentManageView = new MenuStudentManageView(scanner, facultyRepository.getAll());
        MenuCourseRegistrationView menuCourseRegistrationView = new MenuCourseRegistrationView(scanner);
        MenuStudentInformationView menuStudentInformationView = new MenuStudentInformationView(scanner);
        MenuAcademicManagementView menuAcademicManagementView = new MenuAcademicManagementView(scanner);
        MenuGraduationProgressView menuGraduationProgressView = new MenuGraduationProgressView(scanner);
        MenuFacultyManagementView menuFacultyManagementView = new MenuFacultyManagementView(scanner);
        MenuMajorManagementView menuMajorManagementView = new MenuMajorManagementView(scanner);
        MenuSubjectManageView menuSubjectManageView = new MenuSubjectManageView(scanner);
        MenuGradeManageView menuGradeManageView = new MenuGradeManageView(scanner);

        // 2. Initialize Repositories & Services
        StudentService studentService = new StudentService(studentRepository);
        FacultyService facultyService = new FacultyService(facultyRepository);

        SubjectService subjectService = new SubjectService(subjectRepository);
        RegistrationService courseRegistrationService = new RegistrationService(registrationRepository);
        GradeService gradeService = new GradeService(gradeRepository, studentRepository);
        for (Student student : students.values()) {
            gradeService.calculateGpa(student);
        }
        // 3. Initialize Sub-Controllers
        StudentManageController studentManageController = new
                StudentManageController(menuStudentManageView, studentService);

        CourseRegistrationController courseRegistrationController =
                new CourseRegistrationController(menuCourseRegistrationView,
                        courseRegistrationService,
                        studentService,
                        subjectService,
                        menuStudentManageView);

        StudentInformationViewController studentInformationViewController =
                new StudentInformationViewController(menuStudentInformationView, studentService, gradeService);

        GradeManageController gradeManageController = new GradeManageController(
                menuGradeManageView,
                menuStudentManageView,
                menuSubjectManageView,
                subjectService,
                studentService,
                gradeService
        );

        AcademicManagementController academicManagementController =
                new AcademicManagementController(menuAcademicManagementView,
                        gradeManageController,
                        menuStudentManageView,
                        studentService,
                        gradeService,
                        subjectService);

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

        FacultyManagementController facultyManagementController = new FacultyManagementController(
                menuFacultyManagementView,
                facultyService,
                majorManagementController
        );


        //Initialize Main Menu Controller & Run
        MainMenuController mainMenuController = new MainMenuController(
                menuView,
                studentController,
                subjectManageController,
                facultyManagementController);


        mainMenuController.run();
        studentRepository.save();
        facultyRepository.save();
        subjectRepository.save();
        gradeRepository.save();
        registrationRepository.save();
    }
}
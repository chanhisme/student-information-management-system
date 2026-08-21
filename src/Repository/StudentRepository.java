package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import model.Faculty;
import model.Major;
import model.Student;

public class StudentRepository {

    private final Map<String, Student> students;

    public StudentRepository(Map<String, Student> students) {
        this.students = students;
    }

    public void load(FacultyRepository facultyRepository) {
        String filePath = "src/data/students.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String id = null;
            String name = null;
            LocalDate birth = null;
            Student.StudentStatus status = Student.StudentStatus.ACTIVE;
            Faculty faculty = null;
            Major major = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("++++++++++++++++")) {
                    if (id != null && faculty != null) {
                        Student student = new Student(name, id, major, birth, faculty);
                        student.setStatus(status);
                        students.put(id, student);
                    }
                    id = null;
                    name = null;
                    birth = null;
                    status = Student.StudentStatus.ACTIVE;
                    faculty = null;
                    major = null;
                    continue;
                }

                if (line.startsWith("Student ID:")) {
                    id = line.substring("Student ID:".length()).trim();
                } else if (line.startsWith("Name:")) {
                    name = line.substring("Name:".length()).trim();
                } else if (line.startsWith("Date of Birth:")) {
                    String birthStr = line.substring("Date of Birth:".length()).trim();
                    try {
                        birth = LocalDate.parse(birthStr);
                    } catch (Exception e) {
                        birth = null;
                    }
                } else if (line.startsWith("Status:")) {
                    String statusStr = line.substring("Status:".length()).trim();
                    try {
                        status = Student.StudentStatus.valueOf(statusStr);
                    } catch (Exception e) {
                        status = Student.StudentStatus.ACTIVE;
                    }
                } else if (line.startsWith("Faculty ID:")) {
                    String facultyId = line.substring("Faculty ID:".length()).trim();
                    faculty = facultyRepository.getFacultyByPrefix(facultyId);
                } else if (line.startsWith("Major ID:")) {
                    String majorId = line.substring("Major ID:".length()).trim();
                    if (faculty != null) {
                        major = faculty.getMajorById(majorId);
                    }
                }
            }

            if (id != null && faculty != null) {
                Student student = new Student(name, id, major, birth, faculty);
                student.setStatus(status);
                students.put(id, student);
            }

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src/data/students.txt"))) {

            for (Map.Entry<String, Student> entry : students.entrySet()) {
                Student student = entry.getValue();

                writer.write("Student ID: " + student.getId());
                writer.newLine();

                writer.write("Name: " + student.getName());
                writer.newLine();

                writer.write("Date of Birth: " + student.getBirth());
                writer.newLine();

                writer.write("Status: " + student.getStatus());
                writer.newLine();

                writer.write("Faculty ID: " + student.getFaculty().getPrefix());
                writer.newLine();

                if (student.getMajor() != null) {
                    writer.write("Major ID: " + student.getMajor().getId());
                    writer.newLine();
                }

                writer.write("+++++++++++++++++++++++++++++++++++++++++++");
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void add(Student student) {
        students.put(student.getId(), student);
    }

    public Student findById(String id) {
        return students.get(id);
    }

    public void deleteById(String id) {
        students.remove(id);
    }

    public Map<String, Student> getAll() {
        return students;
    }
}
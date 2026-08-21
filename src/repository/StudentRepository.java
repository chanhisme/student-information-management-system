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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            String studentId = null;
            String studentName = null;
            LocalDate birthDate = null;
            Student.StudentStatus studentStatus = Student.StudentStatus.ACTIVE;
            Faculty studentFaculty = null;
            Major studentMajor = null;

            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = currentLine.trim();

                if (currentLine.isEmpty()) {
                    continue;
                }

                if (currentLine.startsWith("++++++++++++++++")) {
                    if (studentId != null && studentFaculty != null) {
                        Student student = new
                                Student(studentName, studentId, studentMajor, birthDate, studentFaculty);

                        student.setStatus(studentStatus);
                        students.put(studentId, student);
                    }
                    studentId = null;
                    studentName = null;
                    birthDate = null;
                    studentStatus = Student.StudentStatus.ACTIVE;
                    studentFaculty = null;
                    studentMajor = null;
                    continue;
                }

                String[] keyAndValue = currentLine.split(":", 2);
                if (keyAndValue.length < 2) {
                    continue;
                }

                String key = keyAndValue[0].trim();
                String value = keyAndValue[1].trim();

                switch (key) {
                    case "Student ID":
                        studentId = value;
                        break;
                    case "Name":
                        studentName = value;
                        break;
                    case "Date of Birth":
                        try {
                            birthDate = LocalDate.parse(value);
                        } catch (Exception e) {
                            birthDate = null;
                        }
                        break;
                    case "Status":
                        try {
                            studentStatus = Student.StudentStatus.valueOf(value);
                        } catch (Exception e) {
                            studentStatus = Student.StudentStatus.ACTIVE;
                        }
                        break;
                    case "Faculty ID":
                        studentFaculty = facultyRepository.getFacultyByPrefix(value);
                        break;
                    case "Major ID":
                        if (studentFaculty != null) {
                            studentMajor = studentFaculty.getMajorById(value);
                        }
                        break;
                }
            }

            if (studentId != null && studentFaculty != null) {
                Student student = new Student(studentName, studentId, studentMajor, birthDate, studentFaculty);
                student.setStatus(studentStatus);
                students.put(studentId, student);
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
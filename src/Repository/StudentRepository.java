package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Map;
import java.util.TreeMap;

import model.Student;

public class StudentRepository {

    private final Map<String, Student> students;

    public StudentRepository(Map<String, Student> students) {
        this.students = students;
    }

    public void load() {
        // TXT → TreeMap
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
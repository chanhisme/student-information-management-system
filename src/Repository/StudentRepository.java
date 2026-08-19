package repository;

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
        // TreeMap → TXT
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
}
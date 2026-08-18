package Repository;

import java.util.Map;
import java.util.TreeMap;
import Model.Student;

public class StudentRepository {

    private final Map<String, Student> students = new TreeMap<>();

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
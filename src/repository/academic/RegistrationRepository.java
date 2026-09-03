package repository.academic;

import DataStructure.MyLinkedList;
import model.student.Student;
import model.subject.Subject;
import view.ConsoleColor;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class RegistrationRepository {

    private final String FILE_PATH = "src/data/registered";
    private final ArrayList<Student> students;
    private final Map<String , Subject> subjectMap;
    public RegistrationRepository(ArrayList<Student> students, Map<String, Subject> subjectMap) {
        this.students = students;
        this.subjectMap = subjectMap;
    }

    public void save() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            writer.write("StudentID|SubjectID|");
            writer.newLine();
            for(Student student : students){
                for(Subject subject : student.getRegisteredSubjects()){
                    writer.write(student.getId() + "|" + subject.getId());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();

            String line;
            while( (line = reader.readLine()) != null){
                String[] parts = line.split("\\|");

                String studentId = parts[0];
                String subjectId = parts[1];
                for (Student student : students) {
                    if (student.getId().equals(studentId)) {
                        student.getRegisteredSubjects().addLast(subjectMap.get(subjectId));
                        break;
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load grades.", e);
        }
    }

    public void add(Student student, Subject subject) {
        student.getRegisteredSubjects().addLast(subject);
    }


    public void delete(Student student, Subject subject) {
        student.getRegisteredSubjects().remove(subject);
    }
}

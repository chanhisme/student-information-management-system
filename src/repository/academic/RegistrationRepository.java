package repository.academic;

import model.student.Student;
import model.subject.Subject;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class RegistrationRepository {

    private final String FILE_PATH = "src/data/registered.txt";
    private final ArrayList<Student> students;
    private final Map<String , Subject> subjectMap;
    public RegistrationRepository(ArrayList<Student> students, Map<String, Subject> subjectMap) {
        this.students = students;
        this.subjectMap = subjectMap;
    }

    public void save() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("StudentID|SubjectID|");
                writer.newLine();
                for (Student student : students) {
                    for (Subject subject : student.getRegisteredSubjects()) {
                        writer.write(student.getId() + "|" + subject.getId());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("StudentID|SubjectID|");
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create registration file.", e);
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
            throw new RuntimeException("Failed to load registrations.", e);
        }
    }

    public void add(Student student, Subject subject) {
        student.getRegisteredSubjects().addLast(subject);
    }


    public void delete(Student student, Subject subject) {
        student.getRegisteredSubjects().remove(subject);
    }
}

package service.subject;

import model.subject.Subject;
import repository.subject.SubjectRepository;

import java.util.ArrayList;

public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void addSubject(Subject subject) {
        if (subjectRepository.findById(subject.getId()) != null) {
            throw new IllegalArgumentException("Subject ID already exists.");
        }
        subjectRepository.add(subject);
        subjectRepository.save();
    }

    public Subject findById(String id) {
        return subjectRepository.findById(id);
    }

    public ArrayList<Subject> getAllSubjects() {
        return subjectRepository.getAll();
    }

    public void updateSubject(Subject subject) {
        if (subjectRepository.findById(subject.getId()) == null) {
            throw new IllegalArgumentException("Subject not found.");
        }
        subjectRepository.save();
    }

    public void deleteSubject(String id) {
        if (subjectRepository.findById(id) == null) {
            throw new IllegalArgumentException("Subject not found.");
        }
        subjectRepository.deleteById(id);
        subjectRepository.save();
    }

    public String generateSubjectId() {
        int id = 1;
        while (subjectRepository.findById("SUB" + id) != null) {
            id++;
        }
        return "SUB" + id;
    }
}

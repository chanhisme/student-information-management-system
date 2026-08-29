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

    public java.util.List<Subject> searchSubjects(String query) {
        java.util.List<Subject> result = new java.util.ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return result;
        }
        String lowerQuery = query.trim().toLowerCase();
        for (Subject subject : subjectRepository.getAll()) {
            if (subject.getId().toLowerCase().contains(lowerQuery) || subject.getName().toLowerCase().contains(lowerQuery)) {
                result.add(subject);
            }
        }
        return result;
    }
}


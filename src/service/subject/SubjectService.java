package service.subject;

import model.subject.Subject;
import repository.subject.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void addSubject(Subject subject) {
        if (!isValidSubjectId(subject.getId())) {
            throw new IllegalArgumentException("Subject ID must be SUB + number, e.g. SUB1.");
        }
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
        if (!isValidSubjectId(subject.getId())) {
            throw new IllegalArgumentException("Subject ID must be SUB + number, e.g. SUB1.");
        }
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

    public boolean isValidSubjectId(String id) {
        return id != null && id.trim().toUpperCase().matches("SUB[1-9][0-9]*");
    }

    public List<Subject> searchSubjects(String query) {
        List<Subject> result = new ArrayList<>();
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
    public Map<String, Subject> getSubjectsMap() {
        return subjectRepository.getSubjectsMap();
    }
}


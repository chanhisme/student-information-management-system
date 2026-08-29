package model.student;

import java.time.LocalDate;

import DataStructure.MyLinkedList;
import model.faculty.Faculty;
import model.faculty.Major;
import model.subject.Subject;

public class Student {
    public enum StudentStatus {
        ACTIVE,
        ON_LEAVE,
        SUSPENDED,
        WITHDRAWN
    }

    private String name;
    private String id;
    private Major major;
    private LocalDate birth;
    private StudentStatus status;
    private Faculty faculty;
    private double gpa;
    private MyLinkedList<Subject> registeredSubjects = new MyLinkedList<>();
    private java.util.Map<String, Double> subjectGrades = new java.util.HashMap<>();

    public Student(String name, String id, Major major, LocalDate birth, Faculty faculty) {
        this.name = name;
        this.id = id;
        this.major = major;
        this.birth = birth;
        this.status = StudentStatus.ACTIVE;
        this.faculty = faculty;
        this.gpa = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public MyLinkedList<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public void setRegisteredSubjects(MyLinkedList<Subject> registeredSubjects) {
        this.registeredSubjects = registeredSubjects;
    }

    public java.util.Map<String, Double> getSubjectGrades() {
        return subjectGrades;
    }

    public void setSubjectGrades(java.util.Map<String, Double> subjectGrades) {
        this.subjectGrades = subjectGrades;
    }
}

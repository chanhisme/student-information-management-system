package model;

import java.time.LocalDate;

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

    public Student(String name, String id, Major major, LocalDate birth, Faculty faculty) {
        this.name = name;
        this.id = id;
        this.major = major;
        this.birth = birth;
        this.status = StudentStatus.ACTIVE;
        this.faculty = faculty;
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
}

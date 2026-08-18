package model;

import java.util.Date;

public class Student {
    public enum StudentStatus {
        ACTIVE,
        ON_LEAVE,
        SUSPENDED,
        WITHDRAWN
    }

    private String name;
    private String id;
    private String major;
    private Date birth;
    private StudentStatus status;
    private Faculty faculty;

    public Student(String name, String id, String major, Date birth, Faculty faculty) {
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
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

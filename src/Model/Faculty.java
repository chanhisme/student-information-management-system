package model;

import java.util.ArrayList;

public class Faculty {

    private String prefix;
    private String name;
    private final ArrayList<Major> majors;

    public Faculty(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
        this.majors = new ArrayList<>();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Major> getMajors() {
        return majors;
    }

    public void addMajor(Major major) {
        majors.add(major);
    }
}
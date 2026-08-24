package model.faculty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Faculty {

    private String prefix;
    private String name;
    private final Map<String, Major> majors;

    public Faculty(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
        this.majors = new LinkedHashMap<>();
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
        return new ArrayList<>(majors.values());
    }

    public Major getMajorById(String id) {
        return majors.get(id);
    }

    public void addMajor(Major major) {
        majors.put(major.getId(), major);
    }
}

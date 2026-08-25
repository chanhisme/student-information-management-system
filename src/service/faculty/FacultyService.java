package service.faculty;

import model.faculty.Faculty;
import repository.faculty.FacultyRepository;

import java.util.ArrayList;

public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public ArrayList<Faculty> getAllFaculty() {
        return facultyRepository.getAll();
    }
}

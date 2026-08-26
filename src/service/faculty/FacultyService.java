package service.faculty;

import model.faculty.Faculty;
import repository.faculty.FacultyRepository;

import java.util.ArrayList;

public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public void addFaculty(Faculty faculty){
        if(findByPreFix(faculty.getPrefix()) != null){
            throw new RuntimeException("This faculty is existed");
        }
        facultyRepository.addFaculty(faculty);
        facultyRepository.save();;
    }
    public Faculty findByPreFix(String preFix){
        return facultyRepository.findByPreFix(preFix);
    }
    public ArrayList<Faculty> getAllFaculty() {
        return facultyRepository.getAll();
    }

    public void deleteFaculty(String preFix){
        if(facultyRepository.findByPreFix(preFix) == null){
            throw new IllegalArgumentException("This faculty id is not existed");
        }
        facultyRepository.deleteFaculty(preFix);
        facultyRepository.save();

    }

    public void updateFaculty(Faculty faculty) {
        if(facultyRepository.findByPreFix(faculty.getPrefix()) == null){
            throw new IllegalArgumentException("This faculty id is not existed");
        }
        facultyRepository.save();
    }
}

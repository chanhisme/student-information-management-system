package service.faculty;

import model.faculty.Faculty;
import model.faculty.Major;
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
    public void updateMajor(Major major, String preFix){
        if(facultyRepository.findMajorById(major.getId(), preFix) == null){
            throw new IllegalArgumentException("This major id is not existed");
        }
        facultyRepository.save();
    }

    public Major findMajorById(String id, String preFix){
        return facultyRepository.findMajorById(id, preFix);
    }
    public void addMajor(Major major, String preFix){
        if(findByPreFix(preFix) == null){
            throw new RuntimeException("This faculty is not existed");
        }
        if(findMajorById(major.getId(), preFix) != null ){
            throw new RuntimeException("This major is existed");
        }
        facultyRepository.addMajor(major, preFix);
        facultyRepository.save();
    }

    public String generateMajorId(Faculty faculty){
        int id = 1;
        while(faculty.getMajorById( faculty.getPrefix() + id) != null){
            ++id;
        }
        return faculty.getPrefix() + id;
    }

    public void deleteMajor(String id, String preFix){
        if(findMajorById(id, preFix) == null ){
            throw new RuntimeException("This major is not existed");
        }
        facultyRepository.deleteMajor(id, preFix);
        facultyRepository.save();
    }
}

package service.student;

import model.student.Student;
import repository.student.StudentRepository;

import java.util.*;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        if (studentRepository.findById(student.getId()) != null) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        studentRepository.add(student);
        studentRepository.save();
    }

    public Student findById(String id) {
        return studentRepository.findById(id);
    }

    public Map<String, Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public List<Student> getStudentsSorted(java.util.Comparator<Student> comparator) {
        List<Student> sortedList = new ArrayList<>(studentRepository.getAll().values());
        if (!sortedList.isEmpty()) {
            mergeSort(sortedList, 0, sortedList.size() - 1, comparator);
        }
        return sortedList;
    }

    private void mergeSort(List<Student> list, int left, int right, Comparator<Student> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid, comparator);
            mergeSort(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }

    private void merge(List<Student> list, int left, int mid, int right, Comparator<Student> comparator) {
        java.util.List<Student> temp = new java.util.ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (comparator.compare(list.get(i), list.get(j)) <= 0) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.add(list.get(i));
            i++;
        }

        while (j <= right) {
            temp.add(list.get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    public void updateStudent(Student student) {
        if (studentRepository.findById(student.getId()) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.save();
    }

    public void deleteStudent(String id) {
        if (studentRepository.findById(id) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.deleteById(id);
        studentRepository.save();
    }

    public List<Student> findStudentByName(String name) {
        String[] targetName = name.trim().split("\\s+");
        List<Student> result = new ArrayList<>();

        for (Student student : studentRepository.getAll().values()) {
            String[] currentName = student.getName().trim().split("\\s+");

            if (targetName.length > currentName.length) {
                continue;
            }

            boolean isFound = true;

            for (int i = 1; i <= targetName.length; i++) {
                String target = targetName[targetName.length - i];
                String current = currentName[currentName.length - i];

                if (!target.equalsIgnoreCase(current)) {
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                result.add(student);
            }
        }

        return result;
    }

    public int getNumberOfStudents(){
        return studentRepository.getNumberOfStudents();
    }
    public List<Student> getStudents(){
        return studentRepository.getStudents();
    }
}

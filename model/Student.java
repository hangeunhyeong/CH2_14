package camp.model;

import java.util.*;

public class Student {
    private final String studentId;
    private String studentName;
    private String studentFeeling;
    private List<Subject> mandatorySubjects;
    private List<Subject> optionalSubjects;

    public Student(String seq, String studentName, String studentFeeling) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentFeeling  = studentFeeling;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public List<Subject> getMandatorySubjects() {return mandatorySubjects;}
    public List<Subject> getOptionalSubjects() {return optionalSubjects;}
    public String getStudentFeeling() {return studentFeeling;}
    public List<String> getSubjects() {
        List<String> subjectNames = new ArrayList<>();
        for (Subject subject : mandatorySubjects) {
            subjectNames.add(subject.getSubjectName());
        }
        for (Subject subject : optionalSubjects) {
            subjectNames.add(subject.getSubjectName());
        }
        return subjectNames;
    }
    //
    public void setMandatorySubjects(List<Subject> mandatorySubjects) {this.mandatorySubjects = mandatorySubjects;}
    public void setOptionalSubjects(List<Subject> optionalSubjects) {this.optionalSubjects = optionalSubjects;}
    public void editStudentInfo(String newName, String newFeeling) {
        this.studentName = newName;
        this.studentFeeling = newFeeling;
    }
}
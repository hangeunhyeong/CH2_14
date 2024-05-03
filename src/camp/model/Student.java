package camp.model;

import java.util.List;

public class Student {
    private final String studentId;
    private final String studentName;
    private List<Subject> mandatorySubjects;
    private List<Subject> optionalSubjects;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
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

    public void setMandatorySubjects(List<Subject> mandatorySubjects) {this.mandatorySubjects = mandatorySubjects;}
    public void setOptionalSubjects(List<Subject> optionalSubjects) {this.optionalSubjects = optionalSubjects;}
}

package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private String studentFeeling;

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

    public String getStudentFeeling() { return studentFeeling; }

}

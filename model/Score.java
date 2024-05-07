package camp.model;

import java.util.Scanner;

public class Score {
    private String scoreId;
    public String subjectId;
    public String studentId;
    public int score;


    public Score(String seq) {
        this.scoreId = seq;
    }

    public Score(String sequence, String studentId, String subjectId, int round, int score) {
        this.scoreId = sequence;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score = score;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getSubjectId() {
        return subjectId;
    }


    public String getStudentId() {
        return studentId;
    }
    public int getScore() {
        return score;
    }
}

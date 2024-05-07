package camp.model;

public class Score {
    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private double score;
    private String rank;

    public Score(String seq,String subjectId,String studentId,int round,double score,String rank) {
        this.scoreId = seq;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.score = score;
        this.rank = rank;
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
    public int getRound() {
        return round;
    }
    public double getScore() {
        return score;
    }
    public String getRank() {
        return rank;
    }

}

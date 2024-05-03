package camp.model;

import java.util.HashMap;
import java.util.List;

public class Score {
    private String studentId;
    private List<Subject> mandatorySubjects;
    private List<Subject> optionalSubjects;
    private HashMap<String, HashMap<String, List>> Scoremap;

    public Score(String seq) {
        this.studentId = seq;
    }
    // Getter
    public String getScoreId() {
        return studentId;
    }
    /*
    public void setScoremap(List<Subject> mandatorysubjects, List<Subject> optionalsubjects) {
        for (Subject subject : mandatorysubjects) {
            Scoremap.put(subject.getSubjectId(), subject.getSubjectType())
        }
    } */

}

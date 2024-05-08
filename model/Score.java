package camp.model;

import java.util.HashMap;
import java.util.*;

public class Score {
    private String studentId;
    private List<Subject> mandatorySubjects;
    private List<Subject> optionalSubjects;
    private HashMap<String, List<List<String>>> scoreMap;

    public Score(String seq, List<Subject> mandatorysubjects, List<Subject> optionalsubjects) {
        this.studentId = seq;
        this.scoreMap = new HashMap<>();
        this.mandatorySubjects = mandatorysubjects;
        this.optionalSubjects = optionalsubjects;
    }
    // Getter
    public String getScoreId() {
        return studentId;
    }

    public HashMap<String, List<List<String>>> getScoreMap() {
        return scoreMap;
    }

    public List<String> getSubjects() {
        List<String> subjectIndex = new ArrayList<>();;
        for (Subject subject : mandatorySubjects) {
            String subjectId = subject.getSubjectId();
            String subjectName = subject.getSubjectName();
            subjectIndex.add(subjectId + " : " + subjectName);
        }
        for (Subject subject : optionalSubjects) {
            String subjectId = subject.getSubjectId();
            String subjectName = subject.getSubjectName();
            subjectIndex.add(subjectId + " : " + subjectName);
        }
        return  subjectIndex;
    }

    public boolean isValidSubjectId(String subjectId) {
        for (Subject subject : mandatorySubjects) {
            if (subject.getSubjectId().equals(subjectId)) {
                return true;
            }
        }
        for (Subject subject : optionalSubjects) {
            if (subject.getSubjectId().equals(subjectId)) {
                return true;
            }
        }
        return false;
    }

    public void initScoreMap() {
        initScoreMapForSubjects(this.mandatorySubjects);
        initScoreMapForSubjects(this.optionalSubjects);
    }

    private void initScoreMapForSubjects(List<Subject> subjects) {
        for (Subject subject : subjects) {
            List<List<String>> scores = new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                List<String> roundScores = new ArrayList<>();
                roundScores.add(String.valueOf(i));
                roundScores.add(null);
                roundScores.add(null);
                scores.add(roundScores);
            }
            scoreMap.put(subject.getSubjectId(), scores);
        }
    }

    public boolean registerScore(String subjectid, int round, int score, String flag) {
        String type = "Optional";
        List<List<String>> scores = scoreMap.get(subjectid);
        List<String> roundScores = scores.get(round - 1);

        for (Subject subject : mandatorySubjects) {
            if (subject.getSubjectId().equals(subjectid)) {
                type = "Mandatory";
                break;
            }
        }

        // 등록시 이미 점수가 존재하는 경우
        if (roundScores.get(1) != null && Objects.equals(flag, "register")) {
            System.out.println("이미 점수가 등록되었습니다.");
        } else if (roundScores.get(1) == null && Objects.equals(flag, "edit")) {
            System.out.println("등록된 점수가 없습니다.");    // 수정시 이전에 등록된 점수가 없는 경우
        } else {
            roundScores.set(1, String.valueOf(score));
            String grade;
            if (type.equals("Mandatory")) {
                grade = calculateMandatory(score);
            } else {
                grade = calculateOptional(score);
            }
            roundScores.set(2, grade);
            return true;
        }
        return false;
    }

    // 필수과목 등급
    public static String calculateMandatory(int score) {
        if (score >= 95) {
            return "A";
        } else if (score >= 90) {
            return "B";
        } else if (score >= 80) {
            return "C";
        } else if (score >= 70) {
            return "D";
        } else if (score >= 60) {
            return "F";
        } else {
            return "N";
        }
    }
    // 선택과목 등급
    public static String calculateOptional(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else if (score >= 50) {
            return "F";
        } else {
            return "N";
        }
    }
}

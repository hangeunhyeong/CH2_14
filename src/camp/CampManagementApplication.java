package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.println("5. 상태별 수강생 조회");
            System.out.println("6. 수강생 삭제");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> studentInfo();
                case 4 -> editStudent();
                case 5 -> studentPerStatus();
                case 6 -> deleteStudentAndScore();
                case 7 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        System.out.println("현재 상태를 입력해 주세요.");
        System.out.println("좋음 :  Green / 안 좋음 : Red / 그저 그럼 : Yellow");
        String studentFeeling = "";
        boolean flag = false;
        while (!flag) {
            System.out.print("\n상태(Green, Red, Yellow) : ");
            studentFeeling = sc.next();
            if (studentFeeling.equals("Yellow") || studentFeeling.equals("Red") || studentFeeling.equals("Green")) {
                flag = true;
            } else {
                System.out.println("Green / Red / Yellow 중 입력해주세요.");
            }
        }


        List<Subject> mandatorySubjects = selectMandatorySubjects();
        List<Subject> optionalSubjects = selectOptionalSubjects();
        String studentId = sequence(INDEX_TYPE_STUDENT);

        Student student = new Student(studentId, studentName, studentFeeling);
        student.setMandatorySubjects(mandatorySubjects);
        student.setOptionalSubjects(optionalSubjects);

        Score score = new Score(studentId, student.getMandatorySubjects(), student.getOptionalSubjects());
        score.initScoreMap();

        studentStore.add(student);
        scoreStore.add(score);
        System.out.println("수강생 등록 성공!\n");
    }

    private static List<Subject> selectMandatorySubjects() {
        System.out.println("필수 과목 목록:");
        List<Subject> mandatorySubjects = new ArrayList<>();
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.println(subject.getSubjectId() + ": " + subject.getSubjectName());
                mandatorySubjects.add(subject);
            }
        }
        System.out.println("필수 과목을 선택하세요 (3개 이상, 선택이 끝나면 exit 입력):");
        List<Subject> selectedSubjects = new ArrayList<>();

        while (true) {
            System.out.print("과목 번호 입력: ");
            String subjectId = sc.next();

            if (subjectId.equals("exit")) {
                if (selectedSubjects.size() >= 3) break;
                else {
                    System.out.println("3개 이상의 필수 과목을 선택해야 합니다.");
                    continue;
                }
            }
            boolean matched = false;
            for (Subject subject : mandatorySubjects){
                if (subject.getSubjectId().equals(subjectId)) {
                    matched = true;
                    selectedSubjects.add(subject);
                    break;
                }
            }
            if (!matched) {
                System.out.println("잘못된 과목 번호입니다. 다시 입력해주세요.");
            }
        }
        return selectedSubjects;
    }

    private static List<Subject> selectOptionalSubjects() {
        System.out.println("선택 과목 목록:");
        List<Subject> choiceSubjects = new ArrayList<>();
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                System.out.println(subject.getSubjectId() + ": " + subject.getSubjectName());
                choiceSubjects.add(subject);
            }
        }
        System.out.println("선택 과목을 선택하세요 (2개 이상, 선택이 끝나면 exit 입력):");
        List<Subject> selectedSubjects = new ArrayList<>();

        while (true) {
            System.out.print("과목 번호 입력: ");
            String subjectId = sc.next();
            if (subjectId.equals("exit")) {
                if (selectedSubjects.size() >= 2) break;
                else {
                    System.out.println("2개 이상의 필수 과목을 선택해야 합니다.");
                    continue;
                }
            }
            boolean matched = false;
            for (Subject subject : choiceSubjects){
                if (subject.getSubjectId().equals(subjectId)) {
                    matched = true;
                    selectedSubjects.add(subject);
                    break;
                }
            }
            if (!matched) {
                System.out.println("잘못된 과목 번호입니다. 다시 입력해주세요.");
            }
        }
        return selectedSubjects;
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : studentStore){
            System.out.println("studentId : " + student.getStudentId()  + " Name : " + student.getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");

    }

    private static void studentInfo() {
        String studentId;
        Student targetStudent;

        do {
            System.out.println("조회할 사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            String finalStudentId = studentId;
            targetStudent = studentStore.stream()
                    .filter(s -> s.getStudentId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetStudent == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetStudent == null);

        System.out.println("고유번호 : " + targetStudent.getStudentId());
        System.out.println("이름 : " + targetStudent.getStudentName());
        System.out.println("상태 : " + targetStudent.getStudentFeeling());
        System.out.println("선택한 과목명 : " + targetStudent.getSubjects());
    }

    private static void editStudent() {
        String studentId;
        Student targetStudent;

        do {
            System.out.println("수정할 사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            String finalStudentId = studentId;
            targetStudent = studentStore.stream()
                    .filter(s -> s.getStudentId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetStudent == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetStudent == null);

        System.out.print("이름 : ");
        String newName = sc.next();

        String newFeeling = "";
        boolean flag = false;
        while (!flag) {
            System.out.print("\n상태(Green, Red, Yellow) : ");
            newFeeling = sc.next();
            if (newFeeling.equals("Yellow") || newFeeling.equals("Red") || newFeeling.equals("Green")) {
                flag = true;
            } else {
                System.out.println("Green / Red / Yellow 중 입력해주세요.");
            }
        }
        targetStudent.editStudentInfo(newName, newFeeling);
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId;
        Score targetScore;

        do {
            System.out.println("등록할 사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            String finalStudentId = studentId;
            targetScore = scoreStore.stream()
                    .filter(s -> s.getScoreId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetScore == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetScore == null);

        System.out.println("시험 점수를 등록합니다...");

        String subjectId;
        do {
            System.out.println("과목 ID를 입력하세요: ");
            subjectId = sc.next();

        } while (!targetScore.isValidSubjectId(subjectId));

        int round;
        do {
            System.out.println("회차를 입력하세요 (1~10): ");
            round = sc.nextInt();
        } while (round < 1 || round > 10);

        int score;
        do {
            System.out.println("점수를 입력하세요 (0~100): ");
            score = sc.nextInt();
        } while (score < 0 || score > 100);

        targetScore.registerScore(subjectId, round, score, "register");
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId;
        Score targetScore;

        do {
            System.out.println("수정할 사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            String finalStudentId = studentId;
            targetScore = scoreStore.stream()
                    .filter(s -> s.getScoreId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetScore == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetScore == null);

        System.out.println("시험 점수를 수정합니다...");

        String subjectId;
        do {
            System.out.println("과목 ID를 입력하세요: ");
            subjectId = sc.next();

        } while (!targetScore.isValidSubjectId(subjectId));

        int round;
        do {
            System.out.println("수정할 회차를 입력하세요 (1~10): ");
            round = sc.nextInt();
        } while (round < 1 || round > 10);

        int score;
        do {
            System.out.println("수정할 점수를 입력하세요 (0~100): ");
            score = sc.nextInt();
        } while (score < 0 || score > 100);

        targetScore.registerScore(subjectId, round, score, "edit");
        System.out.println("\n점수 수정 완료!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId;
        Score targetScore;

        do {
            System.out.println("사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            String finalStudentId = studentId;
            targetScore = scoreStore.stream()
                    .filter(s -> s.getScoreId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetScore == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetScore == null);

        String subjectId;
        do {
            System.out.println("과목 ID를 입력하세요: ");
            subjectId = sc.next();

        } while (!targetScore.isValidSubjectId(subjectId));

        System.out.println("회차별 등급을 조회합니다...");
        List<List<String>> scoreList = targetScore.getScoreMap().get(subjectId);
        for (List<String> roundInfo : scoreList) {
            String roundNumber = roundInfo.get(0); // 회차
            String score = roundInfo.get(1); // 점수
            String grade = roundInfo.get(2); // 등급

            System.out.println("회차: " + roundNumber + ", 점수: " + score + ", 등급: " + grade);
        }
        System.out.println("\n등급 조회 성공!");
    }

    private static void studentPerStatus(){
        List<Student> targetStudents = new ArrayList<>();
        System.out.println("학생들의 상태(Green, Red, Yellow)별 조회를 시작합니다.");
        System.out.println("조회할 상태를 입력하세요 (Green, Red, Yellow): ");
        String status = "";
        boolean flag = false;
        while (!flag) {
            System.out.print("\n상태(Green, Red, Yellow) : ");
            status = sc.next();
            if (status.equals("Yellow") || status.equals("Red") || status.equals("Green")) {
                flag = true;
            } else {
                System.out.println("Green / Red / Yellow 중 입력해주세요.");
            }
        }

        for (Student student : studentStore) {
            if (student.getStudentFeeling().equals(status)) {
                targetStudents.add(student);
            }
        }
        if (targetStudents.isEmpty()) {
            System.out.println("해당 상태의 학생이 없습니다.");
        } else {
            System.out.println("조회된 학생들의 정보:");
            for (Student student : targetStudents) {
                System.out.println("studentId: " + student.getStudentId() + ", studentName: " + student.getStudentName());
            }
        }
    }

    private static void deleteStudentAndScore() {
        String studentId;
        Student targetStudent;
        Score targetScore;

        do {
            System.out.println("삭제할 사용자의 ID를 입력하세요: ");
            studentId = getStudentId();
            final String finalStudentId = studentId;
            targetStudent = studentStore.stream()
                    .filter(s -> s.getStudentId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            targetScore = scoreStore.stream()
                    .filter(score -> score.getScoreId().equals(finalStudentId))
                    .findFirst()
                    .orElse(null);
            if (targetStudent == null) {
                System.out.println("등록되지 않은 사용자입니다.");
            }
        } while (targetStudent == null);

        if (targetScore != null) {scoreStore.remove(targetScore);}
        else {System.out.println("삭제할 점수가 없습니다.");}
        studentStore.remove(targetStudent);
        System.out.println("수강생 및 점수 삭제가 완료되었습니다.");
    }

}

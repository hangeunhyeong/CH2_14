package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

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
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static List<Student> StuList = new ArrayList<>();

    private static void createStudent() {
        Subject subject = null;
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentId = sequence(INDEX_TYPE_STUDENT);
        String studentName = sc.next();
        Student student = new Student(studentId, studentName); // 수강생 인스턴스 생성 예시 코드
        //1.과목 정보 입력
        System.out.print("과목 입력: ");
        String subjectName = sc.next();
        //2.입력 받은 과목 정보를 통해 과목 조회
        for (int i = 0; i < subjectStore.size(); i++) {
            if (subjectName.equals(subjectStore.get(i).getSubjectName())) {
                subject = subjectStore.get(i);
                student.setSubject(subject);
            }
        }
        //3.입력 받은 값 검증
        // 기능 구현 (필수 과목, 선택 과목)
        // 기능 구현
        StuList.add(student);
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        for (Student student : StuList) {
            System.out.print("학생ID: " + student.getStudentId() + " | 학생이름: " + student.getStudentName());
            for (Subject subject : student.getSubject()) {
                System.out.println(" | 과목ID: " + subject.getSubjectId() + " | 과목이름: " + subject.getSubjectName() + " | 과목타입: " + subject.getSubjectType());
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급을 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급을 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGradeBySubject();//수강생의 과목별 평균 등급을 조회
                case 5 -> inquireMyMendatorySubjectAverage(); // 특정 상태 수강생들의 필수 과목 평균 등급을 조회
                case 6 -> flag = false; // 메인 화면 이동
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
    private static List<Score> ScoreList = new ArrayList<>();

    private static void createScore() {
        String scoreId = sequence(INDEX_TYPE_SCORE);
        System.out.print("과목 아이디를 입력해주세요 : ");
        String subjectId = sc.next();
        String studentId = getStudentId();
        System.out.print("회차를 입력해주세요 : ");
        int round = sc.nextInt();
        System.out.print("점수를 입력해주세요 : ");
        double sujectscore = sc.nextDouble();
        String rank;
        if (sujectscore <= 100 && sujectscore >= 95) {
            rank = "A";
        } else if (sujectscore <= 94 && sujectscore >= 90) {
            rank = "B";
        } else if (sujectscore <= 89 && sujectscore >= 80) {
            rank = "C";
        } else if (sujectscore <= 79 && sujectscore >= 70) {
            rank = "D";
        } else {
            rank = "F";
        }
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        Score score = new Score(scoreId, subjectId, studentId, round, sujectscore, rank);
        ScoreList.add(score);
        scoreStore.add(score);
        System.out.println("\n점수 등록 성공!");
        System.out.println("점수id: " + score.getScoreId() + " | 학생ID: " + score.getStudentId() + " | 과목ID: " + score.getSubjectId() + " | 회차: " + score.getRound() + " | 점수: " + score.getScore() + " | 등급: " + score.getRank());
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.print("과목ID를 입력하세요: ");
        String subjectId = sc.next();
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        for (Score score : scoreStore) {
            if (studentId.equals(score.getStudentId()) && subjectId.equals(score.getSubjectId())) {
                System.out.println("회차별 등급: " + score.getRound() + " | " + score.getRank());
            }
        }
        System.out.println("\n등급 조회 성공!");
    }

    //수강생의 과목별 평균 등급을 조회
    private static void inquireAverageGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.print("과목ID를 입력하세요: ");
        String subjectId = sc.next();
        for (Subject subject : subjectStore) {
            if (subjectId.equals(subject.getSubjectId())) {
                System.out.println("과목명: " + subject.getSubjectName());
            }
        }
        double temp = 0;
        int denominator = 0;

        for (Score score : scoreStore) {
            if (studentId.equals(score.getStudentId()) && subjectId.equals(score.getSubjectId())) {

                denominator += 1;
                temp += score.getScore();

            }
        }
        double average = temp / denominator;
        String rank;
        if (average <= 100 && average >= 95) {
            rank = "A";
        } else if (average <= 94 && average >= 90) {
            rank = "B";
        } else if (average <= 89 && average >= 80) {
            rank = "C";
        } else if (average <= 79 && average >= 70) {
            rank = "D";
        } else {
            rank = "F";
        }

        System.out.println("평균 등급: " + rank);
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    //특정 상태 수강생들의 필수 과목 평균 등급을 조회
    //회차는 어떻게 해야하나
    //
    private static void inquireMyMendatorySubjectAverage() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 필수 과목)
        System.out.print("과목ID를 입력하세요: ");
        String subjectType = "MANDATORY";
        // 기능 구현
        double temp = 0;
        int denominator = 0;

        for (Student student : studentStore) {
            if (studentId.equals(student.getStudentId())) {
                System.out.println("학생이름: " + student.getStudentName());
            }
        }
        for (Subject subject : subjectStore) {
            if (subjectType.equals(subject.getSubjectType())) {
                System.out.println("수강하고 있는 필수과목: " + subject.getSubjectName());
            }
        }
        for (Score score : scoreStore) {
            if (studentId.equals(score.getStudentId())) {
                denominator += 1;
                temp += score.getScore();
            }
        }


        double average = temp / denominator;
        String rank;
        if (average <= 100 && average >= 95) {
            rank = "A";
        } else if (average <= 94 && average >= 90) {
            rank = "B";
        } else if (average <= 89 && average >= 80) {
            rank = "C";
        } else if (average <= 79 && average >= 70) {
            rank = "D";
        } else {
            rank = "F";
        }

        System.out.println("평균 등급: " + rank);

        System.out.println("\n등급 조회 성공!");
    }

}

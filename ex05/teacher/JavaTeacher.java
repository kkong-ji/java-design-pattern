package ex05.teacher;

public class JavaTeacher extends Teacher {

    // 재정의
    void 강의하기() {   // 동적바인딩 (C#에서 동적 결함)
        System.out.println("Java 강의하기");
    }
}

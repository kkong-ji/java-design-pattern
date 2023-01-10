package ex04;

/*
 * 문지기를 딱 한명만 만들고 싶을 때
 */

public class DoorMan {

    // 자바에서 static은 main 메소드를 호출하기 전에 JVM이 먼저 읽어서 메모리에 올라온다.
    private static DoorMan doorMan = new DoorMan();
    
    public static DoorMan getInstance() {
        return doorMan;
    }

    private DoorMan() {

    }
    
    public void 쫓아내(Animal a) {
        System.out.println(a.getName() + "쫓아내");
    }
}
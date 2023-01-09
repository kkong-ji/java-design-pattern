package ex03;

/**
 * 어댑터 패턴
 * : 외부 요소를 기존 시스템에 재사용하고 싶지만 아직 만들어지지 않은 경우
 * : 외부 요소를 기존 시스템에 재사용하고 싶지만 호환되지 않는 경우
 */

public class App {
    public static void main(String[] args) {
        Mouse m = new Mouse();
        Cat c = new Cat();
        TigerAdapter ot = new TigerAdapter(new OuterTiger());
        DoorManProxy dm = new DoorManProxy(new DoorMan());
        dm.쫓아내(m);
        dm.쫓아내(c);
        dm.쫓아내(ot);
    }
}

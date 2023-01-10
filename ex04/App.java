package ex04;

public class App {
    public static void main(String[] args) {
        DoorMan doorMan = DoorMan.getInstance();
        doorMan.쫓아내(new Cat());
    }
}

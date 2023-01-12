package ex06.notification;

// wrapper가 없는 Decorator
public class BasicNotifier implements Notifier{
    
    // 이 친구는 뭔가를 의존하면 안된다.

    @Override
    public void send() {
        System.out.println("기본 알림");
    }
}
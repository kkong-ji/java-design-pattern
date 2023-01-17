package ex07;

// 팩토리 패턴 도입하기
public class App {
    public static void main(String[] args) {
        // 싱글톤 제작
        DBFactory dbFactory = DBFactory.getInstance();
        
        // 내가 MariaDB 객체를 알 필요가 없고
        // setUrl 부터 해야할지, execute부터 해야할지 생각할 필요가 없다.
        // 문자열만 추가해서 DB를 생성하기만 하면됌
        // (내가 의존해야할 것은 오직 DBFactory)

        DB db = dbFactory.createDB("maria");
        db.execute("select");
    }
}

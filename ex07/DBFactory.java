package ex07;

public class DBFactory {
    
    // 싱글톤 패턴
    private static DBFactory dbFactory = new DBFactory();

    private DBFactory(){
    }

    public static DBFactory getInstance() {
        return dbFactory;
    }

    // OCP 위배 (기존 코드를 수정)
    // but, OCP를 위배하면 프로그램을 잘못 만든 것이라고 생각할 필요는 없다.
    // 패턴은 우리가 코드를 짤 때 더 편하게, 협업, 클린한 코드를 짤 수 있도록 해주는 것이기 때문에 예외는 있음.
    // DB 회사가 매일 변경된다던지, 새로운 driver가 금방 나온다던지 그렇지 않음. 몇 년에 한 번 수정하면 된다. 

    public DB createDB(String protocol) {
        if(protocol.equals("maria")) {
            DB mariaDB = new MariaDB();
            mariaDB.setUrl("jdbc:mariadb://");
            return mariaDB;
        } else if (protocol.equals("oracle")) {
            DB oracleDB = new oracleDB();
            oracleDB.setUrl("jdbc:oracle:thin://");
            return oracleDB;
        } else if (protocol.equals("mysql")) {
            DB mysqlDB = new MySqlDB();
            mysqlDB.setUrl("jdbc:oracle://");
            return mysqlDB;    
        } else {
            throw new NullPointerException("db driver not found exception");
        }
    }
}

package ex07;

public interface DB {
    public int execute(String sql);
    public void setUrl(String url);
}

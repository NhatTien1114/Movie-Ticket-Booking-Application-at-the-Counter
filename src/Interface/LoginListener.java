package Interface;

public interface LoginListener {
    boolean onLoginAttempt(String username, String password);
    void onRegisterRequest();
    void onShowLogin();
}
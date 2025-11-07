package Interface;

import Entity.NhanVien;

public interface LoginListener {
    boolean onLoginAttempt(String username, String password);
    void onRegisterRequest();
    void onShowLogin();
    boolean onRegisterAttempt(NhanVien nv);
}
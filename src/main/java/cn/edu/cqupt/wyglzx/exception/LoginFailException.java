package cn.edu.cqupt.wyglzx.exception;

public class LoginFailException extends BaseException {
    public LoginFailException() {
        super(ERROR_LOGIN, "login fail");
    }

    public LoginFailException(String message) {
        super(ERROR_LOGIN, message);
    }
}

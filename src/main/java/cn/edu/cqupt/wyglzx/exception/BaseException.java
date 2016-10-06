package cn.edu.cqupt.wyglzx.exception;

public class BaseException extends RuntimeException {
    public static final int ERROR_404 = -404;
    public static final int ERROR_500 = -500;
    public static final int ERROR = -1;
    public static final int ERROR_IN_INTERCEPTOR = -2;

    public static final int ERROR_PARAM_NOT_SET = 1;
    public static final int ERROR_EXISTS = 2;
    public static final int ERROR_NOT_EXISTS = 3;
    public static final int ERROR_LOGIN = 4;
    public static final int WRONG_PASSWORD = 5;
    public static final int INVALID_PARAMS = 6;
    public static final int NOT_ALLOWED = 7;
    public static final int UPLOAD_FAILED = 8;
    public static final int METER_INPUT_WARNING = 9;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseException(int code, String message) {
        super("" + code + ": " + message);
        this.code = code;
        this.message = message;
    }
}

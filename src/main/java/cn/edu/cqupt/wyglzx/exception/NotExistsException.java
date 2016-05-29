package cn.edu.cqupt.wyglzx.exception;

public class NotExistsException extends BaseException {

    public NotExistsException() {
        super(ERROR_NOT_EXISTS, "not exists");
    }

    public NotExistsException(String message) {
        super(ERROR_NOT_EXISTS, message);
    }
}

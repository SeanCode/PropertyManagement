package cn.edu.cqupt.wyglzx.exception;

/**
 * Created by cc on 16/7/6.
 */
public class NotAllowedException extends BaseException {

    public NotAllowedException(String message) {
        super(NOT_ALLOWED, message);
    }

    public NotAllowedException() {
        this("not allowed");
    }
}

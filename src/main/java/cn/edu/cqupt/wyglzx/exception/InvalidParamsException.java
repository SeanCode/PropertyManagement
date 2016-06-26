package cn.edu.cqupt.wyglzx.exception;

/**
 * Created by cc on 16/6/24.
 */
public class InvalidParamsException extends BaseException {

    public InvalidParamsException() {
        super(INVALID_PARAMS, "invalid params");
    }

    public InvalidParamsException(String param) {
        super(INVALID_PARAMS, "invalid params: " + param);
    }
}

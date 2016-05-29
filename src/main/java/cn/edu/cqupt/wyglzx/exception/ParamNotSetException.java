package cn.edu.cqupt.wyglzx.exception;


public class ParamNotSetException extends BaseException {
    public ParamNotSetException(String param) {
        super(ERROR_PARAM_NOT_SET,  String.format("param not set: %s", param));
    }
}

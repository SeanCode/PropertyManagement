package cn.edu.cqupt.wyglzx.exception;

/**
 * Created by cc on 16/10/7.
 */
public class MeterInputException extends BaseException {

    public MeterInputException(String message) {
        super(METER_INPUT_WARNING, message);
    }

    public MeterInputException() {
        super(METER_INPUT_WARNING, "录入警告");
    }
}

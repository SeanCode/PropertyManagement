package cn.edu.cqupt.wyglzx.common;

public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse()
    {
        this(0);
    }

    public ErrorResponse(int code)
    {
        this(code, null);
    }

    public ErrorResponse(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

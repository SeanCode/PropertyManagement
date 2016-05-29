package cn.edu.cqupt.wyglzx.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;

public class DataResponse {
    private int code;
    private Map<String, Object> data;

    public DataResponse()
    {
        this(0);
    }

    public DataResponse(int code)
    {
        this.code = code;
        this.data = new HashMap<String, Object>();
    }

    public DataResponse put(String key, Object object)
    {
        this.data.put(key, object);
        return this;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, Object> getData() {
        return data;
    }
}

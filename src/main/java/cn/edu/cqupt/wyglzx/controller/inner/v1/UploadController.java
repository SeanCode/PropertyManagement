package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.UploadService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/31.
 */
@RestController("Private.UploadController")
@RequestMapping("/api/private/v1/upload")
@Component
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping("/attachment")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse uploadAttachment() {

        DataResponse response = new DataResponse();
        return response;
    }

    @RequestMapping("/file")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse uploadFile() {

        return new DataResponse();
    }

}

package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.PhotoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cc on 16/7/31.
 */
@RestController("Private.PhotoController")
@RequestMapping("/api/private/v1/photo")
@Component
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping("/{type}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getCoverList(@PathVariable(value = "type") Integer type, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {

        DataResponse response = new DataResponse();
        response.put("cover_list", photoService.getCoverList(type, page, 10));
        response.put("count", photoService.getCoverAmount(type));
        return response;
    }

    @RequestMapping("/{type}/{cover_id}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPhotoList(@PathVariable("type") Integer type, @PathVariable("cover_id") Long coverId, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {

        DataResponse response = new DataResponse();
        response.put("photo_list", photoService.getPhotos(coverId, page));
        response.put("count", photoService.getPhotoAmount(coverId));
        return response;
    }

    @RequestMapping("/photo-delete")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse deletePhoto(@RequestParam("id")Long id) {

        photoService.deletePhoto(id);
        return new DataResponse();
    }

    @RequestMapping("/cover-delete")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse deleteCover(@RequestParam("id")Long id) {

        photoService.deleteCover(id);
        return new DataResponse();
    }

}

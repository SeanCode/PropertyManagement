package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.PhotoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/31.
 */
@RestController("Public.PhotoController")
@RequestMapping("/api/public/v1/photo")
@Component
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping("/")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPhotoLatest() {

        return new DataResponse().put("photo_list", photoService.getLatestPhotos());
    }

    @RequestMapping("/covers/")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getCoverLatest() {
        return new DataResponse().put("cover_list", photoService.getLatestCovers());
    }

    @RequestMapping("/{type}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getCoverList(@PathVariable(value = "type") Integer type, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {

        DataResponse response = new DataResponse();
        response.put("cover_list", photoService.getCoverList(type, page, 4));
        response.put("count", photoService.getCoverAmount(type));
        return response;
    }

    @RequestMapping("/{type}/{cover_id}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPhotoList(@PathVariable("type") Integer type, @PathVariable("cover_id") Long coverId, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {

        DataResponse response = new DataResponse();
        response.put("cover", photoService.getCover(coverId));
        response.put("photo_list", photoService.getPhotos(coverId, page));
        response.put("count", photoService.getPhotoAmount(coverId));
        return response;
    }

}

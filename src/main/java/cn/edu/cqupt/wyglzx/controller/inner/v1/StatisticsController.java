package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.StatService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/10/7.
 */
@RestController("Private.StatisticsController")
@RequestMapping("/api/private/v1/stat")
@Component
public class StatisticsController {

    @Autowired
    StatService statService;


    @RequestMapping("/owner")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPersonalStat(@RequestParam("owner_id") Long ownerId,
                                        @RequestParam("owner_type")int ownerType,
                                        @RequestParam("type")int type) {

        DataResponse response = new DataResponse();
        response.put("stat_list", statService.getStatByOwner(ownerId, ownerType, type));
        return response;
    }

    @RequestMapping("/node")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeStat(@RequestParam("node_id") Long nodeId,
                                    @RequestParam("type")int type) {

        DataResponse response = new DataResponse();
        response.put("stat_list", statService.getStatByNode(nodeId, type));
        return response;
    }

}

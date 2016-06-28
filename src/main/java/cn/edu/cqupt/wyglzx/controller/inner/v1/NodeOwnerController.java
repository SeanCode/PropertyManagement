package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.entity.NodeOwnerEntity;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.service.NodeOwnerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController
@RequestMapping("/api/private/v1/node-owner")
public class NodeOwnerController {

    @Autowired
    NodeOwnerService nodeOwnerService;

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addNodeOwner(@RequestParam("node_id") Long nodeId,
                                     @RequestParam("owner_id") Long ownerId,
                                     @RequestParam("owner_type") Integer ownerType) {

        return new DataResponse().put("node_owner", nodeOwnerService.addNodeOwner(nodeId, ownerId, ownerType));
    }

    @RequestMapping("/invalid")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse invalidNodeOwner(@RequestParam("node_id") Long nodeId,
                                         @RequestParam("owner_id") Long ownerId,
                                         @RequestParam("owner_type") Integer ownerType) {

        return new DataResponse().put("node_owner", nodeOwnerService.invalidNodeOwner(nodeId, ownerId, ownerType));
    }

    @RequestMapping("/node")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeByOwnerId(@RequestParam("owner_id") Long ownerId,
                                         @RequestParam("owner_type") Integer ownerType) {

        return new DataResponse().put("node_owner", nodeOwnerService.getNodeByOwner(ownerId, ownerType));
    }

    @RequestMapping("/owner")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getOwnerByNode(@RequestParam("node_id") Long nodeId) {

        return new DataResponse().put("node_owner", nodeOwnerService.getOwnerByNode(nodeId));
    }

}

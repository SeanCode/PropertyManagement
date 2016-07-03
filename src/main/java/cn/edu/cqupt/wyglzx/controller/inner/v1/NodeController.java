package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.NodeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController
@RequestMapping("/api/private/v1/node")
public class NodeController {

    @Autowired
    NodeService nodeService;

    @RequestMapping("/children")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getNodeChildren(@RequestParam("id") Long parentId, @RequestParam(name = "type", required = false, defaultValue = "0") Integer type) {

        return new DataResponse().put("children", nodeService.getNodeChildren(parentId, type));
    }

    @RequestMapping("/tree-root")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getNodeTreeRoot() {
        return new DataResponse().put("tree_root", nodeService.getNodeTreeRoot());
    }

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addNode(@RequestParam("name") String name,
                                @RequestParam("parent_id") Long parentId,
                                @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                @RequestParam(value = "path", required = false, defaultValue = "") String path,
                                @RequestParam("type") Integer type,
                                @RequestParam(value = "area", required = false, defaultValue = "0") Double area,
                                @RequestParam(value = "price", required = false, defaultValue = "0") Double price,
                                @RequestParam(value = "fee", required = false, defaultValue = "0") Double fee,
                                @RequestParam(value = "ownership", required = false, defaultValue = "") String ownership,
                                @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("node", nodeService.addNode(name, parentId, code, path, type, area, price, fee, ownership, remark));
    }

    @RequestMapping("/copy")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse copyNode(@RequestParam("parent_id") Long parentId,
                                 @RequestParam("id") Long id) {

        return new DataResponse().put("node", nodeService.copyNode(parentId, id));
    }

    @RequestMapping("/remove")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse removeNode(@RequestParam("parent_id") Long parentId,
                                   @RequestParam("id") Long id) {

        nodeService.removeNode(parentId, id);
        return new DataResponse();
    }

    @RequestMapping("/delete")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse deleteNode(@RequestParam("id") Long id) {

        nodeService.deleteNode(id);
        return new DataResponse();
    }

    @RequestMapping("/detail")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeDetail(@RequestParam("id") Long id) {

        return new DataResponse().put("node", nodeService.getNodeDetail(id));
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateNodeInfo(@RequestParam("id") Long id,
                                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                       @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                       @RequestParam(value = "path", required = false, defaultValue = "") String path,
                                       @RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
                                       @RequestParam(value = "area", required = false, defaultValue = "0") Double area,
                                       @RequestParam(value = "price", required = false, defaultValue = "0") Double price,
                                       @RequestParam(value = "fee", required = false, defaultValue = "0") Double fee,
                                       @RequestParam(value = "ownership", required = false, defaultValue = "") String ownership,
                                       @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("node", nodeService.updateNodeInfo(id, name, code, path, type, area, price, fee, ownership, remark));
    }

}

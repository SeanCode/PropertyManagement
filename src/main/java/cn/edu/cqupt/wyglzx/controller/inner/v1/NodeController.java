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


    @RequestMapping("/detail")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeDetail(@RequestParam("id") Long id) {

        return new DataResponse().put("node", nodeService.getNodeDetail(id));
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateNodeInfo(@RequestParam("id") Long id,
                                       @RequestParam("name") String name,
                                       @RequestParam("code") String code,
                                       @RequestParam("path") String path,
                                       @RequestParam("type") Integer type,
                                       @RequestParam("area") Double area,
                                       @RequestParam("price") Double price,
                                       @RequestParam("fee") Double fee,
                                       @RequestParam("ownership") String ownership,
                                       @RequestParam("remark") String remark) {

        return new DataResponse().put("node", nodeService.updateNodeInfo(id, name, code, path, type, area, price, fee, ownership, remark));
    }

}

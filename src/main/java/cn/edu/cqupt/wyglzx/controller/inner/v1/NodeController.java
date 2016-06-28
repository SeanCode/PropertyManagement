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

}

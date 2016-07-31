package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.MeterService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController("Private.MeterController")
@RequestMapping("/api/private/v1/meter")
@Component
public class MeterController {

    @Autowired
    MeterService meterService;

    @RequestMapping("/normal-list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeNormalMeterList(@RequestParam("node_id") Long nodeId) {

        return new DataResponse().put("meter_normal_list", meterService.getNodeNormalMeterList(nodeId));
    }

    @RequestMapping("/check-list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeCheckMeterList(@RequestParam("node_id") Long nodeId) {

        return new DataResponse().put("meter_check_list", meterService.getNodeCheckMeterList(nodeId));
    }

    @RequestMapping("/children")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getNodeChildrenMeter(@RequestParam("node_id") Long nodeId) {

        return new DataResponse().put("meter_children", meterService.getNodeChildrenMeter(nodeId));
    }

    @RequestMapping("/add-normal")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addNormalMeter(@RequestParam("name") String name,
                                       @RequestParam("node_id") Long nodeId,
                                       @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                       @RequestParam("type") Integer type,
                                       @RequestParam(value = "rate", required = false, defaultValue = "1") Integer rate,
                                       @RequestParam(value = "begin", required = false, defaultValue = "0") Double begin,
                                       @RequestParam(value = "nameplate", required = false, defaultValue = "") String nameplate,
                                       @RequestParam(value = "manufacturers", required = false, defaultValue = "") String manufacturers,
                                       @RequestParam(value = "purchaser", required = false, defaultValue = "") String purchaser,
                                       @RequestParam(value = "cost", required = false, defaultValue = "0") Double cost,
                                       @RequestParam(value = "buy_time", required = false, defaultValue = "0") Long buyTime,
                                       @RequestParam(value = "product_time", required = false, defaultValue = "0") Long productTime,
                                       @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("meter", meterService.addNormalMeter(name, nodeId, code, type, rate, begin, nameplate, manufacturers, purchaser, cost, buyTime, productTime, remark));
    }

    @RequestMapping("/add-check")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addCheckMeter(@RequestParam("name") String name,
                                      @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                      @RequestParam("type") Integer type,
                                      @RequestParam("parent_id") Long parentId,
                                      @RequestParam(value = "rate", required = false, defaultValue = "1") Integer rate,
                                      @RequestParam(value = "begin", required = false, defaultValue = "0") Double begin,
                                      @RequestParam(value = "nameplate", required = false, defaultValue = "") String nameplate,
                                      @RequestParam(value = "manufacturers", required = false, defaultValue = "") String manufacturers,
                                      @RequestParam(value = "purchaser", required = false, defaultValue = "") String purchaser,
                                      @RequestParam(value = "cost", required = false, defaultValue = "0") Double cost,
                                      @RequestParam(value = "buy_time", required = false, defaultValue = "0") Long buyTime,
                                      @RequestParam(value = "product_time", required = false, defaultValue = "0") Long productTime,
                                      @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("meter", meterService.addCheckMeter(name, code, type, parentId, rate, begin, nameplate, manufacturers, purchaser, cost, buyTime, productTime, remark));
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateMeterInfo(@RequestParam("id") Long id,
                                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                        @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                        @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {
        return new DataResponse().put("meter", meterService.updateMeter(id, name, code, remark));
    }

    @RequestMapping("/detail")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getMeterDetail(@RequestParam("id") Long id) {

        return new DataResponse().put("meter", meterService.getMeterDetail(id));
    }

    @RequestMapping("/remove")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse removeMeter(@RequestParam("id") Long id, @RequestParam("node_id") Long nodeId) {

        meterService.removeMeter(id, nodeId);
        return new DataResponse();
    }

    @RequestMapping("/replace")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse replaceMeter(@RequestParam("id") Long id,
                                     @RequestParam("end") Double end,
                                     @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                     @RequestParam(value = "code", required = false, defaultValue = "") String code,
                                     @RequestParam(value = "rate", required = false, defaultValue = "1") Integer rate,
                                     @RequestParam(value = "begin", required = false, defaultValue = "0") Double begin,
                                     @RequestParam(value = "nameplate", required = false, defaultValue = "") String nameplate,
                                     @RequestParam(value = "manufacturers", required = false, defaultValue = "") String manufacturers,
                                     @RequestParam(value = "purchaser", required = false, defaultValue = "") String purchaser,
                                     @RequestParam(value = "cost", required = false, defaultValue = "0") Double cost,
                                     @RequestParam(value = "buy_time", required = false, defaultValue = "0") Long buyTime,
                                     @RequestParam(value = "product_time", required = false, defaultValue = "0") Long productTime,
                                     @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        meterService.replaceMeter(id, end, name, code, rate, begin, nameplate, manufacturers, purchaser, cost, buyTime, productTime, remark);
        return new DataResponse();
    }

    @RequestMapping("/set-as-child")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse setMeterAsChild(@RequestParam("id") Long id, @RequestParam("parent_id") Long parentId, @RequestParam("child_node_id") Long nodeId) {
        meterService.setAsChild(id, nodeId, parentId);
        return new DataResponse();
    }

    @RequestMapping("/set-as-check")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse setMeterAsCheck(@RequestParam("id") Long id, @RequestParam("node_id") Long nodeId) {
        meterService.setAsCheck(id, nodeId);

        return new DataResponse();
    }

}

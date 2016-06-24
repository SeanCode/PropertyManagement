package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "meter", schema = "sdq", catalog = "")
public class MeterEntity {
    private long id;
    private long nodeId = 0;
    private long checkMeterId = 0;
    private String code = "";
    private String name = "";
    private long parentId = 0;
    private int type = 0;
    private double rate = 0.00;
    private double begin = 0.00;
    private String nameplate = "";
    private String manufacturers = "";
    private String purchaser = "";
    private double cost = 0.00;
    private long buyTime = 0;
    private long productTime = 0;
    private String remark = "";
    private int status = 0;
    private int weight = 0;
    private long createTime = 0;
    private long updateTime = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "node_id", nullable = false)
    @JsonProperty("node_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "check_meter_id", nullable = false)
    @JsonProperty("check_meter_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getCheckMeterId() {
        return checkMeterId;
    }

    public void setCheckMeterId(long checkMeterId) {
        this.checkMeterId = checkMeterId;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 255)
    @JsonProperty("code")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    @JsonProperty("parent_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 2)
    @JsonProperty("rate")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "begin", nullable = false, precision = 0)
    @JsonProperty("begin")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getBegin() {
        return begin;
    }

    public void setBegin(double begin) {
        this.begin = begin;
    }

    @Basic
    @Column(name = "nameplate", nullable = false, length = 200)
    @JsonProperty("nameplate")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getNameplate() {
        return nameplate;
    }

    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    @Basic
    @Column(name = "manufacturers", nullable = false, length = 99)
    @JsonProperty("manufacturers")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    @Basic
    @Column(name = "purchaser", nullable = false, length = 99)
    @JsonProperty("purchaser")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    @Basic
    @Column(name = "cost", nullable = false, precision = 0)
    @JsonProperty("cost")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "buy_time", nullable = false)
    @JsonProperty("buy_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    @Basic
    @Column(name = "product_time", nullable = false)
    @JsonProperty("product_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getProductTime() {
        return productTime;
    }

    public void setProductTime(long productTime) {
        this.productTime = productTime;
    }

    @Basic
    @Column(name = "remark", nullable = false, length = -1)
    @JsonProperty("remark")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Detail.class})
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterEntity that = (MeterEntity) o;

        if (id != that.id) return false;
        if (nodeId != that.nodeId) return false;
        if (checkMeterId != that.checkMeterId) return false;
        if (parentId != that.parentId) return false;
        if (type != that.type) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (Double.compare(that.begin, begin) != 0) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (buyTime != that.buyTime) return false;
        if (productTime != that.productTime) return false;
        if (weight != that.weight) return false;
        if (status != that.status) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nameplate != null ? !nameplate.equals(that.nameplate) : that.nameplate != null) return false;
        if (manufacturers != null ? !manufacturers.equals(that.manufacturers) : that.manufacturers != null)
            return false;
        if (purchaser != null ? !purchaser.equals(that.purchaser) : that.purchaser != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + (int) (checkMeterId ^ (checkMeterId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + type;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(begin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (nameplate != null ? nameplate.hashCode() : 0);
        result = 31 * result + (manufacturers != null ? manufacturers.hashCode() : 0);
        result = 31 * result + (purchaser != null ? purchaser.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (buyTime ^ (buyTime >>> 32));
        result = 31 * result + (int) (productTime ^ (productTime >>> 32));
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + status;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@javax.persistence.Table(name = "meter", schema = "sdq", catalog = "")
public class MeterEntity {
    private long id;

    @Id
    @javax.persistence.Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long nodeId;

    @Basic
    @javax.persistence.Column(name = "node_id")
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    private long checkId;

    @Basic
    @javax.persistence.Column(name = "check_id")
    public long getCheckId() {
        return checkId;
    }

    public void setCheckId(long checkId) {
        this.checkId = checkId;
    }

    private String code;

    @Basic
    @javax.persistence.Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long parentId;

    @Basic
    @javax.persistence.Column(name = "parent_id")
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    private int type;

    @Basic
    @javax.persistence.Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private double price;

    @Basic
    @javax.persistence.Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double rate;

    @Basic
    @javax.persistence.Column(name = "rate")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private double total;

    @Basic
    @javax.persistence.Column(name = "total")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double begin;

    @Basic
    @javax.persistence.Column(name = "begin")
    public double getBegin() {
        return begin;
    }

    public void setBegin(double begin) {
        this.begin = begin;
    }

    private String nameplate;

    @Basic
    @javax.persistence.Column(name = "nameplate")
    public String getNameplate() {
        return nameplate;
    }

    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    private String manufacturers;

    @Basic
    @javax.persistence.Column(name = "manufacturers")
    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    private String purchaser;

    @Basic
    @javax.persistence.Column(name = "purchaser")
    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    private double cost;

    @Basic
    @javax.persistence.Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private long buyTime;

    @Basic
    @javax.persistence.Column(name = "buy_time")
    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    private long productTime;

    @Basic
    @javax.persistence.Column(name = "product_time")
    public long getProductTime() {
        return productTime;
    }

    public void setProductTime(long productTime) {
        this.productTime = productTime;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int weight;

    @Basic
    @javax.persistence.Column(name = "weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int status;

    @Basic
    @javax.persistence.Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private long createTime;

    @Basic
    @javax.persistence.Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    private long updateTime;

    @Basic
    @javax.persistence.Column(name = "update_time")
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
        if (checkId != that.checkId) return false;
        if (parentId != that.parentId) return false;
        if (type != that.type) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (Double.compare(that.total, total) != 0) return false;
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
        result = 31 * result + (int) (checkId ^ (checkId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + type;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
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

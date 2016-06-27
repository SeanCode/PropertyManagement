package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "node", schema = "sdq", catalog = "")
public class NodeEntity {

    private long id;
    private String name = "";
    private long parentId = 0;
    private long rootId = 0;
    private int level = 0;
    private String code = "";
    private String path = "";
    private int type = 0;
    private double area = 0.00;
    private double price = 0.00;
    private double fee = 0.00;
    private String ownership = "";
    private String contract = "";
    private String remark = "";
    private int weight = 0;
    private long createTime = 0;
    private long updateTime = 0;

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_ROOM = 2;
    public static final int TYPE_INSTITUTION = 3;

    private List<NodeEntity> children;
    private boolean isParent = true; //temp true
    private String typeName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 99)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    @JsonProperty("parent_id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "root_id", nullable = false)
    @JsonProperty("root_id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    @Basic
    @Column(name = "level", nullable = false)
    @JsonProperty("level")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 255)
    @JsonProperty("code")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "path", nullable = false, length = 99)
    @JsonProperty("path")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Transient
    @JsonProperty("type_name")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getTypeName() {
        switch (type) {
            case TYPE_NORMAL:
                typeName = "一般";
                break;
            case TYPE_ROOM:
                typeName = "房间";
                break;
            case TYPE_INSTITUTION:
                typeName = "机构";
                break;
            default:
                typeName = "其他";
                break;
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "area", nullable = false, precision = 0)
    @JsonProperty("area")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    @JsonProperty("price")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "fee", nullable = false, precision = 0)
    @JsonProperty("fee")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "ownership", nullable = false, length = 99)
    @JsonProperty("ownership")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    @Basic
    @Column(name = "contract", nullable = false, length = -1)
    @JsonProperty("contract")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Basic
    @Column(name = "remark", nullable = false, length = -1)
    @JsonProperty("remark")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    @JsonProperty("children")
    @JsonView({OutputEntityJsonView.Tree.class})
    public List<NodeEntity> getChildren() {
        return children == null ? new ArrayList<>() : children;
    }

    public void setChildren(List<NodeEntity> children) {
        this.children = children;
    }

    @Transient
    @JsonProperty("isParent")
    @JsonView({OutputEntityJsonView.Tree.class})
    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
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

        NodeEntity that = (NodeEntity) o;

        if (id != that.id) return false;
        if (parentId != that.parentId) return false;
        if (rootId != that.rootId) return false;
        if (level != that.level) return false;
        if (type != that.type) return false;
        if (Double.compare(that.area, area) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.fee, fee) != 0) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (ownership != null ? !ownership.equals(that.ownership) : that.ownership != null) return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + (int) (rootId ^ (rootId >>> 32));
        result = 31 * result + level;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + type;
        temp = Double.doubleToLongBits(area);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ownership != null ? ownership.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

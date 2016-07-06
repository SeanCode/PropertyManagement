package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "record", schema = "sdq", catalog = "")
public class RecordEntity {

    private long id;
    private long meterId = 0;
    private int meterType = 0;
    private long nodeId = 0;
    private int year = 0;
    private int month = 0;
    private double end = 0.00;
    private double begin = 0.00;
    private int type = 0;
    private String reader = "";
    private long operatorId = 0;
    private long reviewerId = 0;
    private String remark = "";
    private int status = STATUS_PENDING;
    private int tag = TAG_INIT;
    private long time = 0;
    private int weight = 0;
    private long createTime = 0;
    private long updateTime = 0;

    private String statusName = "";
    private String tagName = "";

    private NodeEntity node;
    private MeterEntity meter;
    private AdminEntity operator;
    private AdminEntity reviewer;

    public static final int TYPE_ARCHIVE = 1;
    public static final int TYPE_TEMP = 2;

    public static final int TAG_INIT = 0;
    public static final int TAG_NORMAL = 1;
    public static final int TAG_WARNING = 2;
    public static final int TAG_ERROR = 3;

    public static final int STATUS_PENDING = 0;
    public static final int STATUS_APPROVED = 1;
    public static final int STATUS_REJECTED = -1;

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
    @Column(name = "meter_id", nullable = false)
    @JsonProperty("meter_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }

    @Basic
    @Column(name = "meter_type", nullable = false)
    @JsonProperty("meter_type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getMeterType() {
        return meterType;
    }

    public void setMeterType(int meterType) {
        this.meterType = meterType;
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
    @Column(name = "year", nullable = false)
    @JsonProperty("year")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month", nullable = false)
    @JsonProperty("month")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "end", nullable = false, precision = 0)
    @JsonProperty("end")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
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
    @Column(name = "reader", nullable = false)
    @JsonProperty("reader")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    @Basic
    @Column(name = "operator_id", nullable = false)
    @JsonProperty("operator_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "reviewer_id", nullable = false)
    @JsonProperty("reviewer_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
    }

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "reviewer_id", insertable = false, updatable = false)
    @JsonProperty("reviewer")
    @JsonView({OutputEntityJsonView.Detail.class})
    public AdminEntity getReviewer() {
        return reviewer;
    }

    public void setReviewer(AdminEntity reviewer) {
        this.reviewer = reviewer;
    }

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "node_id", insertable = false, updatable = false)
    @JsonProperty("node")
    @JsonView({OutputEntityJsonView.Detail.class})
    public NodeEntity getNode() {
        return node;
    }

    public void setNode(NodeEntity node) {
        this.node = node;
    }

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "meter_id", insertable = false, updatable = false)
    @JsonProperty("meter")
    @JsonView({OutputEntityJsonView.Detail.class})
    public MeterEntity getMeter() {
        return meter;
    }

    public void setMeter(MeterEntity meter) {
        this.meter = meter;
    }

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "operator_id", insertable = false, updatable = false)
    @JsonProperty("operator")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public AdminEntity getOperator() {
        return operator;
    }

    public void setOperator(AdminEntity operator) {
        this.operator = operator;
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
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Transient
    @JsonProperty("status_name")
    @JsonView({OutputEntityJsonView.Detail.class})
    public String getStatusName() {
        switch (status) {
            case STATUS_APPROVED:
                statusName = "审核通过";
                break;
            case STATUS_PENDING:
                statusName = "待审核";
                break;
            case STATUS_REJECTED:
                statusName = "未通过";
                break;
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Transient
    @JsonProperty("tag_name")
    @JsonView({OutputEntityJsonView.Detail.class})
    public String getTagName() {
        switch (tag) {
            case TAG_INIT:
                tagName = "暂无";
                break;
            case TAG_NORMAL:
                tagName = "正常";
                break;
            case TAG_WARNING:
                tagName = "警告";
                break;
            case TAG_ERROR:
                tagName = "错误";
                break;
        }
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Basic
    @Column(name = "tag", nullable = false)
    @JsonProperty("tag")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "time", nullable = false)
    @JsonProperty("time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
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

        RecordEntity that = (RecordEntity) o;

        if (id != that.id) return false;
        if (meterId != that.meterId) return false;
        if (meterType != that.meterType) return false;
        if (nodeId != that.nodeId) return false;
        if (year != that.year) return false;
        if (month != that.month) return false;
        if (Double.compare(that.end, end) != 0) return false;
        if (Double.compare(that.begin, begin) != 0) return false;
        if (type != that.type) return false;
        if (operatorId != that.operatorId) return false;
        if (reviewerId != that.reviewerId) return false;
        if (status != that.status) return false;
        if (tag != that.tag) return false;
        if (time != that.time) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (reader != null ? !reader.equals(that.reader) : that.reader != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (node != null ? !node.equals(that.node) : that.node != null) return false;
        if (meter != null ? !meter.equals(that.meter) : that.meter != null) return false;
        return operator != null ? operator.equals(that.operator) : that.operator == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (meterId ^ (meterId >>> 32));
        result = 31 * result + meterType;
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + year;
        result = 31 * result + (int) (month ^ (month >>> 32));
        temp = Double.doubleToLongBits(end);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(begin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + type;
        result = 31 * result + (reader != null ? reader.hashCode() : 0);
        result = 31 * result + (int) (operatorId ^ (operatorId >>> 32));
        result = 31 * result + (int) (reviewerId ^ (reviewerId >>> 32));
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + tag;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        result = 31 * result + (node != null ? node.hashCode() : 0);
        result = 31 * result + (meter != null ? meter.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }
}

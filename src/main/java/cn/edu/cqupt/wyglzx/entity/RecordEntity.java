package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

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
    private long month = 0;
    private double end = 0.00;
    private double usage = 0.00;
    private long readerId = 0;
    private long operatorId = 0;
    private String reviewer = "";
    private String remark = "";
    private int status = 0;
    private int tag = 0;
    private long time = 0;
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
    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
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
    @Column(name = "usage", nullable = false, precision = 0)
    @JsonProperty("usage")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    @Basic
    @Column(name = "reader_id", nullable = false)
    @JsonProperty("reader_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
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
    @Column(name = "reviewer", nullable = false, length = 99)
    @JsonProperty("reviewer")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
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

        RecordEntity that = (RecordEntity) o;

        if (id != that.id) return false;
        if (meterId != that.meterId) return false;
        if (meterType != that.meterType) return false;
        if (nodeId != that.nodeId) return false;
        if (year != that.year) return false;
        if (month != that.month) return false;
        if (Double.compare(that.end, end) != 0) return false;
        if (Double.compare(that.usage, usage) != 0) return false;
        if (readerId != that.readerId) return false;
        if (operatorId != that.operatorId) return false;
        if (status != that.status) return false;
        if (tag != that.tag) return false;
        if (time != that.time) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (reviewer != null ? !reviewer.equals(that.reviewer) : that.reviewer != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
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
        temp = Double.doubleToLongBits(usage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (readerId ^ (readerId >>> 32));
        result = 31 * result + (int) (operatorId ^ (operatorId >>> 32));
        result = 31 * result + (reviewer != null ? reviewer.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + tag;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

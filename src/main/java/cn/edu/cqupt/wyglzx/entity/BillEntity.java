package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.*;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "bill", schema = "sdq", catalog = "")
public class BillEntity {

    //TODO update bill table Column

    private long id;
    private double money = 0.00;
    private long recordId = 0;
    private long userId = 0;
    private long nodeId = 0;
    private int nodeType = 0;
    private long time = 0;
    private long createTime = 0;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "record_id", nullable = false)
    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "node_id", nullable = false)
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "node_type", nullable = false)
    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (recordId != that.recordId) return false;
        if (userId != that.userId) return false;
        if (nodeId != that.nodeId) return false;
        if (nodeType != that.nodeType) return false;
        if (time != that.time) return false;
        if (createTime != that.createTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (recordId ^ (recordId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + nodeType;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        return result;
    }
}

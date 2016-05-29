package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.*;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@Table(name = "settlement", schema = "sdq", catalog = "")
public class SettlementEntity {
    private long id;
    private long timePriceId;
    private long nodeId;
    private long userId;
    private double money;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_price_id")
    public long getTimePriceId() {
        return timePriceId;
    }

    public void setTimePriceId(long timePriceId) {
        this.timePriceId = timePriceId;
    }

    @Basic
    @Column(name = "node_id")
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettlementEntity that = (SettlementEntity) o;

        if (id != that.id) return false;
        if (timePriceId != that.timePriceId) return false;
        if (nodeId != that.nodeId) return false;
        if (userId != that.userId) return false;
        if (Double.compare(that.money, money) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (timePriceId ^ (timePriceId >>> 32));
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

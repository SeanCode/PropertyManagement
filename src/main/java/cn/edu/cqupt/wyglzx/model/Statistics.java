package cn.edu.cqupt.wyglzx.model;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * Created by cc on 16/10/7.
 */
public class Statistics {

    private int          year;
    private long         ownerId;
    private long         nodeId;
    private List<Double> data;// 用于存放一年内的数据
    private int          type;
    private double       money;
    private String       label;

    public static final int TYPE_WATER = 1;
    public static final int TYPE_ELE   = 2;
    public static final int TYPE_GAS   = 3;

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("owner_id")
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("node_id")
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("data")
    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("label")
    public String getLabel() {
        label = "" + year;
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @JsonProperty("money")
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

        Statistics that = (Statistics) o;

        if (year != that.year) return false;
        if (ownerId != that.ownerId) return false;
        if (nodeId != that.nodeId) return false;
        if (type != that.type) return false;
        if (Double.compare(that.money, money) != 0) return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = year;
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + type;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

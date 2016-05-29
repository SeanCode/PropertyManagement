package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@javax.persistence.Table(name = "record", schema = "sdq", catalog = "")
public class RecordEntity {
    private long id;

    @Id
    @javax.persistence.Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long timePriceId;

    @Basic
    @javax.persistence.Column(name = "time_price_id")
    public long getTimePriceId() {
        return timePriceId;
    }

    public void setTimePriceId(long timePriceId) {
        this.timePriceId = timePriceId;
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

    private long userId;

    @Basic
    @javax.persistence.Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private String userName;

    @Basic
    @javax.persistence.Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private long meterId;

    @Basic
    @javax.persistence.Column(name = "meter_id")
    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }

    private String meterCode;

    @Basic
    @javax.persistence.Column(name = "meter_code")
    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    private String address;

    @Basic
    @javax.persistence.Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String meterType;

    @Basic
    @javax.persistence.Column(name = "meter_type")
    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    private long month;

    @Basic
    @javax.persistence.Column(name = "month")
    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    private long recordTime;

    @Basic
    @javax.persistence.Column(name = "record_time")
    public long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(long recordTime) {
        this.recordTime = recordTime;
    }

    private double end;

    @Basic
    @javax.persistence.Column(name = "end")
    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    private double usage;

    @Basic
    @javax.persistence.Column(name = "usage")
    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
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

    private double money;

    @Basic
    @javax.persistence.Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private String meterReader;

    @Basic
    @javax.persistence.Column(name = "meter_reader")
    public String getMeterReader() {
        return meterReader;
    }

    public void setMeterReader(String meterReader) {
        this.meterReader = meterReader;
    }

    private String operator;

    @Basic
    @javax.persistence.Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    private int meterChanged;

    @Basic
    @javax.persistence.Column(name = "meter_changed")
    public int getMeterChanged() {
        return meterChanged;
    }

    public void setMeterChanged(int meterChanged) {
        this.meterChanged = meterChanged;
    }

    private int checked;

    @Basic
    @javax.persistence.Column(name = "checked")
    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    private double temp;

    @Basic
    @javax.persistence.Column(name = "temp")
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    private int sign;

    @Basic
    @javax.persistence.Column(name = "sign")
    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    private String operatorSupervisor;

    @Basic
    @javax.persistence.Column(name = "operator_supervisor")
    public String getOperatorSupervisor() {
        return operatorSupervisor;
    }

    public void setOperatorSupervisor(String operatorSupervisor) {
        this.operatorSupervisor = operatorSupervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordEntity that = (RecordEntity) o;

        if (id != that.id) return false;
        if (timePriceId != that.timePriceId) return false;
        if (nodeId != that.nodeId) return false;
        if (userId != that.userId) return false;
        if (meterId != that.meterId) return false;
        if (month != that.month) return false;
        if (recordTime != that.recordTime) return false;
        if (Double.compare(that.end, end) != 0) return false;
        if (Double.compare(that.usage, usage) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (meterChanged != that.meterChanged) return false;
        if (checked != that.checked) return false;
        if (Double.compare(that.temp, temp) != 0) return false;
        if (sign != that.sign) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (meterCode != null ? !meterCode.equals(that.meterCode) : that.meterCode != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (meterType != null ? !meterType.equals(that.meterType) : that.meterType != null) return false;
        if (meterReader != null ? !meterReader.equals(that.meterReader) : that.meterReader != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (operatorSupervisor != null ? !operatorSupervisor.equals(that.operatorSupervisor) : that.operatorSupervisor != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (timePriceId ^ (timePriceId >>> 32));
        result = 31 * result + (int) (nodeId ^ (nodeId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (int) (meterId ^ (meterId >>> 32));
        result = 31 * result + (meterCode != null ? meterCode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (meterType != null ? meterType.hashCode() : 0);
        result = 31 * result + (int) (month ^ (month >>> 32));
        result = 31 * result + (int) (recordTime ^ (recordTime >>> 32));
        temp1 = Double.doubleToLongBits(end);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(usage);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + (meterReader != null ? meterReader.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + meterChanged;
        result = 31 * result + checked;
        temp1 = Double.doubleToLongBits(temp);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + sign;
        result = 31 * result + (operatorSupervisor != null ? operatorSupervisor.hashCode() : 0);
        return result;
    }
}

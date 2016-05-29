package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.*;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@Table(name = "operation", schema = "sdq", catalog = "")
public class OperationEntity {
    private long id;
    private long timePriceId;
    private int wrong;
    private int warning;
    private int success;

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
    @Column(name = "wrong")
    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    @Basic
    @Column(name = "warning")
    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    @Basic
    @Column(name = "success")
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationEntity that = (OperationEntity) o;

        if (id != that.id) return false;
        if (timePriceId != that.timePriceId) return false;
        if (wrong != that.wrong) return false;
        if (warning != that.warning) return false;
        if (success != that.success) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (timePriceId ^ (timePriceId >>> 32));
        result = 31 * result + wrong;
        result = 31 * result + warning;
        result = 31 * result + success;
        return result;
    }
}

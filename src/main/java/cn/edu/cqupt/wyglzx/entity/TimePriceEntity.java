package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.*;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@Table(name = "time_price", schema = "sdq", catalog = "")
public class TimePriceEntity {
    private long id;
    private int year;
    private int month;
    private double water;
    private double elec;
    private double elecRete;
    private double gas;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "water")
    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    @Basic
    @Column(name = "elec")
    public double getElec() {
        return elec;
    }

    public void setElec(double elec) {
        this.elec = elec;
    }

    @Basic
    @Column(name = "elec_rete")
    public double getElecRete() {
        return elecRete;
    }

    public void setElecRete(double elecRete) {
        this.elecRete = elecRete;
    }

    @Basic
    @Column(name = "gas")
    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePriceEntity that = (TimePriceEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (month != that.month) return false;
        if (Double.compare(that.water, water) != 0) return false;
        if (Double.compare(that.elec, elec) != 0) return false;
        if (Double.compare(that.elecRete, elecRete) != 0) return false;
        if (Double.compare(that.gas, gas) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + year;
        result = 31 * result + month;
        temp = Double.doubleToLongBits(water);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(elec);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(elecRete);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(gas);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

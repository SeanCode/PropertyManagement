package cn.edu.cqupt.wyglzx.entity;

import javax.persistence.*;

/**
 * Created by cc on 16/5/28.
 */
@Entity
@Table(name = "node", schema = "sdq", catalog = "")
public class NodeEntity {
    private int nid;
    private String nname;
    private String ncode;
    private String nlabel;
    private String nlongname;
    private Integer nlevel;
    private int nparent;
    private String nprotype;
    private Integer nbelong;
    private Double nprice;
    private Double narea;
    private Double nfee;
    private String nower;
    private String nhetong;
    private String noriower;
    private String ncurpeople;
    private String ncurower;
    private String ninfo;
    private int ndeleted;

    @Id
    @Column(name = "nid")
    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "nname")
    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    @Basic
    @Column(name = "ncode")
    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    @Basic
    @Column(name = "nlabel")
    public String getNlabel() {
        return nlabel;
    }

    public void setNlabel(String nlabel) {
        this.nlabel = nlabel;
    }

    @Basic
    @Column(name = "nlongname")
    public String getNlongname() {
        return nlongname;
    }

    public void setNlongname(String nlongname) {
        this.nlongname = nlongname;
    }

    @Basic
    @Column(name = "nlevel")
    public Integer getNlevel() {
        return nlevel;
    }

    public void setNlevel(Integer nlevel) {
        this.nlevel = nlevel;
    }

    @Basic
    @Column(name = "nparent")
    public int getNparent() {
        return nparent;
    }

    public void setNparent(int nparent) {
        this.nparent = nparent;
    }

    @Basic
    @Column(name = "nprotype")
    public String getNprotype() {
        return nprotype;
    }

    public void setNprotype(String nprotype) {
        this.nprotype = nprotype;
    }

    @Basic
    @Column(name = "nbelong")
    public Integer getNbelong() {
        return nbelong;
    }

    public void setNbelong(Integer nbelong) {
        this.nbelong = nbelong;
    }

    @Basic
    @Column(name = "nprice")
    public Double getNprice() {
        return nprice;
    }

    public void setNprice(Double nprice) {
        this.nprice = nprice;
    }

    @Basic
    @Column(name = "narea")
    public Double getNarea() {
        return narea;
    }

    public void setNarea(Double narea) {
        this.narea = narea;
    }

    @Basic
    @Column(name = "nfee")
    public Double getNfee() {
        return nfee;
    }

    public void setNfee(Double nfee) {
        this.nfee = nfee;
    }

    @Basic
    @Column(name = "nower")
    public String getNower() {
        return nower;
    }

    public void setNower(String nower) {
        this.nower = nower;
    }

    @Basic
    @Column(name = "nhetong")
    public String getNhetong() {
        return nhetong;
    }

    public void setNhetong(String nhetong) {
        this.nhetong = nhetong;
    }

    @Basic
    @Column(name = "noriower")
    public String getNoriower() {
        return noriower;
    }

    public void setNoriower(String noriower) {
        this.noriower = noriower;
    }

    @Basic
    @Column(name = "ncurpeople")
    public String getNcurpeople() {
        return ncurpeople;
    }

    public void setNcurpeople(String ncurpeople) {
        this.ncurpeople = ncurpeople;
    }

    @Basic
    @Column(name = "ncurower")
    public String getNcurower() {
        return ncurower;
    }

    public void setNcurower(String ncurower) {
        this.ncurower = ncurower;
    }

    @Basic
    @Column(name = "ninfo")
    public String getNinfo() {
        return ninfo;
    }

    public void setNinfo(String ninfo) {
        this.ninfo = ninfo;
    }

    @Basic
    @Column(name = "ndeleted")
    public int getNdeleted() {
        return ndeleted;
    }

    public void setNdeleted(int ndeleted) {
        this.ndeleted = ndeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeEntity that = (NodeEntity) o;

        if (nid != that.nid) return false;
        if (nparent != that.nparent) return false;
        if (ndeleted != that.ndeleted) return false;
        if (nname != null ? !nname.equals(that.nname) : that.nname != null) return false;
        if (ncode != null ? !ncode.equals(that.ncode) : that.ncode != null) return false;
        if (nlabel != null ? !nlabel.equals(that.nlabel) : that.nlabel != null) return false;
        if (nlongname != null ? !nlongname.equals(that.nlongname) : that.nlongname != null) return false;
        if (nlevel != null ? !nlevel.equals(that.nlevel) : that.nlevel != null) return false;
        if (nprotype != null ? !nprotype.equals(that.nprotype) : that.nprotype != null) return false;
        if (nbelong != null ? !nbelong.equals(that.nbelong) : that.nbelong != null) return false;
        if (nprice != null ? !nprice.equals(that.nprice) : that.nprice != null) return false;
        if (narea != null ? !narea.equals(that.narea) : that.narea != null) return false;
        if (nfee != null ? !nfee.equals(that.nfee) : that.nfee != null) return false;
        if (nower != null ? !nower.equals(that.nower) : that.nower != null) return false;
        if (nhetong != null ? !nhetong.equals(that.nhetong) : that.nhetong != null) return false;
        if (noriower != null ? !noriower.equals(that.noriower) : that.noriower != null) return false;
        if (ncurpeople != null ? !ncurpeople.equals(that.ncurpeople) : that.ncurpeople != null) return false;
        if (ncurower != null ? !ncurower.equals(that.ncurower) : that.ncurower != null) return false;
        if (ninfo != null ? !ninfo.equals(that.ninfo) : that.ninfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nid;
        result = 31 * result + (nname != null ? nname.hashCode() : 0);
        result = 31 * result + (ncode != null ? ncode.hashCode() : 0);
        result = 31 * result + (nlabel != null ? nlabel.hashCode() : 0);
        result = 31 * result + (nlongname != null ? nlongname.hashCode() : 0);
        result = 31 * result + (nlevel != null ? nlevel.hashCode() : 0);
        result = 31 * result + nparent;
        result = 31 * result + (nprotype != null ? nprotype.hashCode() : 0);
        result = 31 * result + (nbelong != null ? nbelong.hashCode() : 0);
        result = 31 * result + (nprice != null ? nprice.hashCode() : 0);
        result = 31 * result + (narea != null ? narea.hashCode() : 0);
        result = 31 * result + (nfee != null ? nfee.hashCode() : 0);
        result = 31 * result + (nower != null ? nower.hashCode() : 0);
        result = 31 * result + (nhetong != null ? nhetong.hashCode() : 0);
        result = 31 * result + (noriower != null ? noriower.hashCode() : 0);
        result = 31 * result + (ncurpeople != null ? ncurpeople.hashCode() : 0);
        result = 31 * result + (ncurower != null ? ncurower.hashCode() : 0);
        result = 31 * result + (ninfo != null ? ninfo.hashCode() : 0);
        result = 31 * result + ndeleted;
        return result;
    }
}

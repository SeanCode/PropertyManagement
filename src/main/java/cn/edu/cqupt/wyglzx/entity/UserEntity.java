package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "user", schema = "sdq", catalog = "")
public class UserEntity {

    private Long id;
    private Long departmentId = 0L;
    private String name = "";
    private String phone = "";
    private String username = "";
    private String password = "";
    private String idCard = "";
    private String schoolCard = "";
    private Integer important = 0;
    private String nickname = "";
    private Integer weight = 0;
    private String remark = "";
    private Long createTime = 0L;
    private Long updateTime = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id", nullable = false)
    @JsonProperty("department_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 80)
    @JsonProperty("phone")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    @JsonProperty("username")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 80)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "id_card", nullable = false, length = 80)
    @JsonProperty("id_card")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "school_card", nullable = false, length = 70)
    @JsonProperty("school_card")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getSchoolCard() {
        return schoolCard;
    }

    public void setSchoolCard(String schoolCard) {
        this.schoolCard = schoolCard;
    }

    @Basic
    @Column(name = "important", nullable = false)
    @JsonProperty("important")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Integer getImportant() {
        return important;
    }

    public void setImportant(Integer important) {
        this.important = important;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 255)
    @JsonProperty("nickname")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "remark", nullable = false, length = 255)
    @JsonProperty("remark")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (schoolCard != null ? !schoolCard.equals(that.schoolCard) : that.schoolCard != null) return false;
        if (important != null ? !important.equals(that.important) : that.important != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        return updateTime != null ? updateTime.equals(that.updateTime) : that.updateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (schoolCard != null ? schoolCard.hashCode() : 0);
        result = 31 * result + (important != null ? important.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}

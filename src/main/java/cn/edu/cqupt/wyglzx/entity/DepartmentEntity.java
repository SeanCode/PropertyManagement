package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "department", schema = "sdq", catalog = "")
public class DepartmentEntity {

    private Long id;
    private String name = "";
    private Long parentId = 0L;
    private Long rootId = 0L;
    private Integer level = 0;
    private Integer weight = 0;
    private Long createTime = 0L;
    private Long updateTime = 0L;

    private List<UserEntity> userList;
    private List<DepartmentEntity> children;

    private boolean isParent = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 99)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    @JsonProperty("parent_id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "root_id", nullable = false)
    @JsonProperty("root_id")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public Long getRootId() {

        if (this.getLevel() == 0) {
            return this.id == null ? 0L : this.id;
        }

        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    @Basic
    @Column(name = "level", nullable = false)
    @JsonProperty("level")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Transient
    @JsonProperty("user_list")
    @JsonView({OutputEntityJsonView.Tree.class})
    public List<UserEntity> getUserList() {
        return userList == null ? new ArrayList<>() : userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    @Transient
    @JsonProperty("children")
    @JsonView({OutputEntityJsonView.Tree.class})
    public List<DepartmentEntity> getChildren() {
        return children == null ? new ArrayList<>() : children;
    }

    public void setChildren(List<DepartmentEntity> children) {
        this.children = children;
    }

    @Transient
    @JsonProperty("isParent")
    @JsonView({OutputEntityJsonView.Tree.class})
    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Tree.class, OutputEntityJsonView.Detail.class})
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (rootId != null ? !rootId.equals(that.rootId) : that.rootId != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (rootId != null ? rootId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}

package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by cc on 16/7/31.
 */
@Entity
@Table(name = "org", schema = "sdq", catalog = "")
public class OrgEntity {

    private long id;
    private long adminId = 0;
    private String name = "";
    private String content = "";
    private int weight = 0;
    private long createTime = 0;
    private long updateTime = 0;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "admin_id", nullable = false)
    @JsonProperty("admin_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 99)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    @JsonProperty("content")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
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

        OrgEntity orgEntity = (OrgEntity) o;

        if (id != orgEntity.id) return false;
        if (adminId != orgEntity.adminId) return false;
        if (weight != orgEntity.weight) return false;
        if (createTime != orgEntity.createTime) return false;
        if (updateTime != orgEntity.updateTime) return false;
        if (name != null ? !name.equals(orgEntity.name) : orgEntity.name != null) return false;
        if (content != null ? !content.equals(orgEntity.content) : orgEntity.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

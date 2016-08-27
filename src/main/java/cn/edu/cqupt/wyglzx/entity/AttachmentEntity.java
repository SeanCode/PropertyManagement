package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by cc on 16/8/21.
 */
@Entity
@Table(name = "attachment", schema = "sdq", catalog = "")
public class AttachmentEntity {
    private long id;
    private String name       = "";
    private String url        = "";
    private int    weight     = 0;
    private long   createTime = 0;
    private long   updateTime = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 199)
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 299)
    @JsonProperty("url")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
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

        AttachmentEntity that = (AttachmentEntity) o;

        if (id != that.id) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

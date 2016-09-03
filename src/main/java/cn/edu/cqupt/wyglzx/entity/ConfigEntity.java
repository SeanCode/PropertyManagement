package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by cc on 16/6/24.
 */
@Entity
@Table(name = "config", schema = "sdq", catalog = "")
public class ConfigEntity {

    private long id;
    private String data       = "";
    private int    type       = 0;
    private long   createTime = 0L;
    private long   updateTime = 0L;

    public final static int TYPE_DEFAULT_PRICE = 10000;
    public final static int TYPE_SPECIAL_PRICE = 10001;
    public final static int TYPE_BANNER        = 20000;

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
    @Column(name = "data", nullable = false)
    @JsonProperty("data")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

        ConfigEntity that = (ConfigEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }

    public static class Price {

        @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
        public double water = 0.00;
        @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
        public double ele   = 0.00;
        @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
        public double gas   = 0.00;
    }
}

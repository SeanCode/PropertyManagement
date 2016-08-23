package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.common.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by cc on 16/7/31.
 */
@Entity
@Table(name = "cover", schema = "sdq", catalog = "")
public class CoverEntity {

    public static final int TYPE_LEADER  = 1;//领导关怀
    public static final int TYPE_HONOR   = 2;//荣誉奖励
    public static final int TYPE_MEETING = 3;//会议纪实
    public static final int TYPE_LATEST  = 4;//物业动态
    public static final int TYPE_SCHOOL  = 5;//校园绿化

    private long   id                    = 0;
    private long   adminId               = 0;
    private String name                  = "";
    private String coverUrl              = "";
    private int    type                  = 0;
    private String typeName              = "";
    private int    weight                = 0;
    private long   createTime            = 0;
    private long   updateTime            = 0;
    private String create_time_formatted = "";
    private String update_time_formatted = "";
    private int read;
    private AdminEntity            admin;

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
    @JsonProperty("admin_id")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "admin_id", nullable = false)
    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "admin_id", insertable = false, updatable = false)
    @JsonProperty("admin")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    @Basic
    @JsonProperty("name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "name", nullable = false, length = 199)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @JsonProperty("cover_url")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "cover_url", nullable = false, length = 299)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Basic
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Transient
    @JsonProperty("type_name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getTypeName() {
        switch (type) {
            case TYPE_HONOR:
                typeName = "荣誉奖励";
                break;
            case TYPE_LATEST:
                typeName = "物业动态";
                break;
            case TYPE_LEADER:
                typeName = "领导关怀";
                break;
            case TYPE_SCHOOL:
                typeName = "校园绿化";
                break;
            case TYPE_MEETING:
                typeName = "会议纪实";
                break;
            default:
                typeName = "物业写真";
                break;
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @JsonProperty("read")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "read", nullable = false)
    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    @Basic
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Detail.class})
    @Column(name = "weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    @Column(name = "create_time", nullable = false)
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Transient
    @JsonProperty("create_time_formatted")
    @JsonView({OutputEntityJsonView.Detail.class})
    public String getCreate_time_formatted() {

        create_time_formatted = Util.getTimeString(this.createTime);
        return create_time_formatted;
    }

    public void setCreate_time_formatted(String create_time_formatted) {
        this.create_time_formatted = create_time_formatted;
    }

    @Basic
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Detail.class})
    @Column(name = "update_time", nullable = false)
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    @JsonProperty("update_time_formatted")
    @JsonView({OutputEntityJsonView.Detail.class})
    public String getUpdate_time_formatted() {
        update_time_formatted = Util.getTimeString(this.updateTime);

        return update_time_formatted;
    }

    public void setUpdate_time_formatted(String update_time_formatted) {
        this.update_time_formatted = update_time_formatted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoverEntity that = (CoverEntity) o;

        if (id != that.id) return false;
        if (adminId != that.adminId) return false;
        if (type != that.type) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (read != that.read) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return coverUrl != null ? coverUrl.equals(that.coverUrl) : that.coverUrl == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coverUrl != null ? coverUrl.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        result = 31 * result + read;
        return result;
    }
}

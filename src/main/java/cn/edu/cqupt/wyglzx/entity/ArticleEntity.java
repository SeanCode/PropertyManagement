package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.common.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by cc on 16/7/31.
 */
@Entity
@Table(name = "article", schema = "sdq", catalog = "")
public class ArticleEntity {

    private long id;
    private long    adminId     = 0;
    private int     type        = 0;
    private String  title       = "";
    private String  content     = "";
    private String  downloadUrl = "";
    private int     read        = 0;
    private int     weight      = 0;
    private long    createTime  = 0;
    private long    updateTime  = 0;
    private Boolean isNew       = false;

    private AdminEntity admin;
    private String      createTimeFormated;

    //服务指南
    public static final int TYPE_GUIDE          = 10000;
    public static final int TYPE_GUIDE_RULE     = 10001;//政策法规
    public static final int TYPE_GUIDE_WORK     = 10002;//工作简报
    public static final int TYPE_GUIDE_PROCESS  = 10003;//流程指南
    public static final int TYPE_GUIDE_DOWNLOAD = 10004;//相关下载
    //新闻
    public static final int TYPE_NEWS           = 20000;
    public static final int TYPE_NEWS_HOT       = 20001;//新闻热点
    public static final int TYPE_NEWS_POST      = 20002;//公示公告
    //安检日志
    public static final int TYPE_LOG            = 30000;
    public static final int TYPE_LOG_DEVICE     = 30001;//特种设备
    public static final int TYPE_LOG_TEACHING   = 30002;//教学巡查
    public static final int TYPE_LOG_DEPARTMENT = 30003;//公寓巡查
    public static final int TYPE_LOG_PROPERTY   = 30004;//物业巡查

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
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @JsonProperty("title")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "title", nullable = false, length = 299)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @JsonProperty("content")
    @JsonView({OutputEntityJsonView.Detail.class})
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content.replaceAll("\r\n", "<br>");
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @JsonProperty("download_url")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "download_url", nullable = false, length = 299)
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Basic
    @JsonProperty("read")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "[read]", nullable = false)
    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    @Basic
    @JsonProperty("weight")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @JsonProperty("create_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "create_time", nullable = false)
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long craeteTime) {
        this.createTime = craeteTime;
    }

    @Basic
    @JsonProperty("update_time")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    @Column(name = "update_time", nullable = false)
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    @JsonProperty("is_new")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public Boolean getNew() {
        isNew = Util.isLatestDays(createTime, 3);
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    @Transient
    @JsonProperty("create_time_formated")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getCreateTimeFormated() {

        createTimeFormated = Util.getTimeString(getCreateTime());
        return createTimeFormated;
    }

    public void setCreateTimeFormated(String createTimeFormated) {
        this.createTimeFormated = createTimeFormated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity that = (ArticleEntity) o;

        if (id != that.id) return false;
        if (adminId != that.adminId) return false;
        if (type != that.type) return false;
        if (read != that.read) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (downloadUrl != null ? !downloadUrl.equals(that.downloadUrl) : that.downloadUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + type;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
        result = 31 * result + read;
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        return result;
    }
}

package cn.edu.cqupt.wyglzx.entity;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.common.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;

/**
 * Created by cc on 16/9/5.
 */
@Entity
@Table(name = "suggestion", schema = "sdq", catalog = "")
public class SuggestionEntity {
    private long   id;
    private int    type;
    private int    status;
    private String content;
    private String userName;
    private String userAccount;
    private String userIp;
    private long   adminId;
    private String reply;
    private int    weight;
    private long   createTime;
    private long   updateTime;

    public static final int TYPE_SUGGESTION = 1;
    public static final int TYPE_OPINION    = 2;

    public static final int STATUS_INIT  = 0;
    public static final int STATUS_REPLY = 1;

    private AdminEntity admin;
    private String      createTimeFormatted;
    private String      updateTimeFormatted;
    private String      typeName;

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
    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
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
            case TYPE_OPINION:
                typeName = "意见";
                break;
            case TYPE_SUGGESTION:
                typeName = "建议";
                break;
            default:
                typeName = "";
                break;
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    @Column(name = "user_name", nullable = false, length = 199)
    @JsonProperty("user_name")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_account", nullable = false, length = 99)
    @JsonProperty("user_account")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "user_ip", nullable = false, length = 99)
    @JsonProperty("user_ip")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
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
    @Column(name = "reply", nullable = false, length = -1)
    @JsonProperty("reply")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    @Transient
    @JsonProperty("create_time_formatted")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getCreateTimeFormatted() {

        createTimeFormatted = Util.getTimeString(new SimpleDateFormat("MM/dd"), getCreateTime());
        return createTimeFormatted;
    }

    public void setCreateTimeFormatted(String createTimeFormatted) {
        this.createTimeFormatted = createTimeFormatted;
    }

    @Transient
    @JsonProperty("update_time_formatted")
    @JsonView({OutputEntityJsonView.Basic.class, OutputEntityJsonView.Detail.class})
    public String getUpdateTimeFormatted() {

        updateTimeFormatted = Util.getTimeString(new SimpleDateFormat("MM/dd"), getUpdateTime());
        return updateTimeFormatted;
    }

    public void setUpdateTimeFormatted(String updateTimeFormatted) {
        this.updateTimeFormatted = updateTimeFormatted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuggestionEntity that = (SuggestionEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (status != that.status) return false;
        if (adminId != that.adminId) return false;
        if (weight != that.weight) return false;
        if (createTime != that.createTime) return false;
        if (updateTime != that.updateTime) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userAccount != null ? !userAccount.equals(that.userAccount) : that.userAccount != null) return false;
        if (userIp != null ? !userIp.equals(that.userIp) : that.userIp != null) return false;
        if (reply != null ? !reply.equals(that.reply) : that.reply != null) return false;
        if (admin != null ? !admin.equals(that.admin) : that.admin != null) return false;
        if (createTimeFormatted != null ? !createTimeFormatted.equals(that.createTimeFormatted) : that.createTimeFormatted != null)
            return false;
        if (updateTimeFormatted != null ? !updateTimeFormatted.equals(that.updateTimeFormatted) : that.updateTimeFormatted != null)
            return false;
        return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type;
        result = 31 * result + status;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (userIp != null ? userIp.hashCode() : 0);
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (reply != null ? reply.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (updateTime ^ (updateTime >>> 32));
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (createTimeFormatted != null ? createTimeFormatted.hashCode() : 0);
        result = 31 * result + (updateTimeFormatted != null ? updateTimeFormatted.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}

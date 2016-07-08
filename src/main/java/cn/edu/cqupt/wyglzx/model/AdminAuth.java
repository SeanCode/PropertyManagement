package cn.edu.cqupt.wyglzx.model;

import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Transient;

/**
 * Created by cc on 16/7/8.
 */
public class AdminAuth {

    @Transient
    private boolean input;
    @Transient
    private boolean check;
    @Transient
    private boolean statistics;
    @Transient
    private boolean owner;
    @Transient
    private boolean institution;
    @Transient
    private boolean advanced;
    @Transient
    private boolean admin;
    @Transient
    private boolean article;
    @Transient
    private boolean picture;
    @Transient
    private boolean resource;
    @Transient
    private boolean banner;

    @Transient
    private Long privilege;

    public AdminAuth(Long privilege) {
        this.privilege = privilege;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("input")
    public boolean isInput() {
        return (privilege & 1) == 1;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("check")
    public boolean isCheck() {
        return (privilege & 2) == 2;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("statistics")
    public boolean isStatistics() {
        return (privilege & 4) == 4;
    }

    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("owner")
    public boolean isOwner() {
        return (privilege & 8) == 8;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("institution")
    public boolean isInstitution() {
        return (privilege & 16) == 16;
    }

    public void setInstitution(boolean institution) {
        this.institution = institution;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("advanced")
    public boolean isAdvanced() {
        return (privilege & 32) == 32;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("admin")
    public boolean isAdmin() {
        return (privilege & 64) == 64;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("article")
    public boolean isArticle() {
        return (privilege & 128) == 128;
    }

    public void setArticle(boolean article) {
        this.article = article;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("picture")
    public boolean isPicture() {
        return (privilege & 256) == 256;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("resource")
    public boolean isResource() {
        return (privilege & 512) == 512;
    }

    public void setResource(boolean resource) {
        this.resource = resource;
    }

    @JsonView({OutputEntityJsonView.Detail.class})
    @JsonProperty("banner")
    public boolean banner() {
        return (privilege & 1024) == 1024;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }
}

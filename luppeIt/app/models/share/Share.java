package models.share;

import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Share extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shareId;
    private String title;
    private String description;
    private String content;
    private String url;
    private Integer luppeCount;
    private Integer digCount;
    private Integer viewCount;
    private Integer categoryId;
    private Integer shareStatusId;
    private Integer resourceId;
    private Integer userId;
    private Date lastModifiedDate;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLuppeCount() {
        return luppeCount;
    }

    public void setLuppeCount(Integer luppeCount) {
        this.luppeCount = luppeCount;
    }

    public Integer getDigCount() {
        return digCount;
    }

    public void setDigCount(Integer digCount) {
        this.digCount = digCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getShareStatusId() {
        return shareStatusId;
    }

    public void setShareStatusId(Integer shareStatusId) {
        this.shareStatusId = shareStatusId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

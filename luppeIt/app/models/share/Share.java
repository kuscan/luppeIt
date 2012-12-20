package models.share;

import models.BaseModel;
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
public class Share extends BaseModel {

	/*
	 * Properties on table
	 */
    private Integer shareId;
    private String title;
    private String description;
    private String content;
    private String url;
    private String author;
    private Integer luppeCount;
    private Integer digCount;
    private Integer viewCount;
    private Integer categoryId;
    private Integer shareStatusId;
    private Integer rssResourceId;
    private Integer userId;
    private Long lastModifiedDate;
    
    /*
     * Properties not on table
     */
    private String resourceName;
    private String categoryName;

    public boolean equals(Object other) {
        if (this.title.equals(((Share)other).getTitle()) && this.url.equals(((Share)other).getUrl())) {
            return true;
        }
        return false;
    }

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

    public Integer getRssResourceId() {
        return rssResourceId;
    }

    public void setRssResourceId(Integer rssResourceId) {
        this.rssResourceId = rssResourceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

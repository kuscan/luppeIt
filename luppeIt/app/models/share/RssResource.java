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
 * Date: 11/26/12
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssResource extends BaseModel {

    private Integer rssResourceId;
    private String rssResourceName;
    private String url;
    private Integer parentResourceId;
    private Integer rssResourceStatusId;
    private Integer categoryId;
    private Integer updateIntervalMinute;
    private Long nextFeedDate;

    public Integer getRssResourceId() {
        return rssResourceId;
    }

    public void setRssResourceId(Integer rssResourceId) {
        this.rssResourceId = rssResourceId;
    }

    public String getRssResourceName() {
        return rssResourceName;
    }

    public void setRssResourceName(String rssResourceName) {
        this.rssResourceName = rssResourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(Integer parentResourceId) {
        this.parentResourceId = parentResourceId;
    }

    public Integer getRssResourceStatusId() {
        return rssResourceStatusId;
    }

    public void setRssResourceStatusId(Integer rssResourceStatusId) {
        this.rssResourceStatusId = rssResourceStatusId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUpdateIntervalMinute() {
        return updateIntervalMinute;
    }

    public void setUpdateIntervalMinute(Integer updateIntervalMinute) {
        this.updateIntervalMinute = updateIntervalMinute;
    }

    public Long getNextFeedDate() {
        return nextFeedDate;
    }

    public void setNextFeedDate(Long nextFeedDate) {
        this.nextFeedDate = nextFeedDate;
    }
}

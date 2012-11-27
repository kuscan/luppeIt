package models.status;

import models.BaseModel;
import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssResourceStatus extends BaseModel {

    private Integer rssResourceStatusId;
    private String rssResourceStatusName;

    public Integer getRssResourceStatusId() {
        return rssResourceStatusId;
    }

    public void setRssResourceStatusId(Integer rssResourceStatusId) {
        this.rssResourceStatusId = rssResourceStatusId;
    }

    public String getRssResourceStatusName() {
        return rssResourceStatusName;
    }

    public void setRssResourceStatusName(String rssResourceStatusName) {
        this.rssResourceStatusName = rssResourceStatusName;
    }
}

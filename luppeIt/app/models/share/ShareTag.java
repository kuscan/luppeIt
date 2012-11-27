package models.share;

import models.BaseModel;
import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShareTag extends BaseModel {

    private Integer shareTagId;
    private Integer shareId;
    private Integer tagId;
    private Integer truth;

    public Integer getShareTagId() {
        return shareTagId;
    }

    public void setShareTagId(Integer shareTagId) {
        this.shareTagId = shareTagId;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTruth() {
        return truth;
    }

    public void setTruth(Integer truth) {
        this.truth = truth;
    }
}

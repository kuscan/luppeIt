package models.share;

import models.BaseModel;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SharePhoto extends BaseModel {

    private Integer sharePhotoId;
    private String url;
    private Integer shareId;

    public Integer getSharePhotoId() {
        return sharePhotoId;
    }

    public void setSharePhotoId(Integer sharePhotoId) {
        this.sharePhotoId = sharePhotoId;
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
}

package models.status;

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
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagStatus extends BaseModel {

    private Integer tagStatusId;
    private String tagStatusName;

    public Integer getTagStatusId() {
        return tagStatusId;
    }

    public void setTagStatusId(Integer tagStatusId) {
        this.tagStatusId = tagStatusId;
    }

    public String getTagStatusName() {
        return tagStatusName;
    }

    public void setTagStatusName(String tagStatusName) {
        this.tagStatusName = tagStatusName;
    }
}

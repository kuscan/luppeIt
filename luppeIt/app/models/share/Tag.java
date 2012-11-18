package models.share;

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
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Tag extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tagId;
    private String tagName;
    private Integer tagStatusId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagStatusId() {
        return tagStatusId;
    }

    public void setTagStatusId(Integer tagStatusId) {
        this.tagStatusId = tagStatusId;
    }
}

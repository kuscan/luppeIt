package models.status;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagStatus extends Model {

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

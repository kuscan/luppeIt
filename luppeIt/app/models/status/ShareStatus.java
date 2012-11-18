package models.status;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShareStatus extends Model {

    private Integer shareStatusId;
    private String shareStatusName;

    public Integer getShareStatusId() {
        return shareStatusId;
    }

    public void setShareStatusId(Integer shareStatusId) {
        this.shareStatusId = shareStatusId;
    }

    public String getShareStatusName() {
        return shareStatusName;
    }

    public void setShareStatusName(String shareStatusName) {
        this.shareStatusName = shareStatusName;
    }
}

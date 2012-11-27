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
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceStatus extends BaseModel {

    private Integer resourceStatusId;
    private String resourceStatusName;

    public Integer getResourceStatusId() {
        return resourceStatusId;
    }

    public void setResourceStatusId(Integer resourceStatusId) {
        this.resourceStatusId = resourceStatusId;
    }

    public String getResourceStatusName() {
        return resourceStatusName;
    }

    public void setResourceStatusName(String resourceStatusName) {
        this.resourceStatusName = resourceStatusName;
    }
}

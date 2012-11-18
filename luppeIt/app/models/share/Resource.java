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
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Resource extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resourceId;
    private String resourceName;
    private Integer resourceStatusId;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getResourceStatusId() {
        return resourceStatusId;
    }

    public void setResourceStatusId(Integer resourceStatusId) {
        this.resourceStatusId = resourceStatusId;
    }
}

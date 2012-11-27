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
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryStatus extends BaseModel {

    private Integer categoryStatusId;
    private String categoryStatusName;

    public Integer getCategoryStatusId() {
        return categoryStatusId;
    }

    public void setCategoryStatusId(Integer categoryStatusId) {
        this.categoryStatusId = categoryStatusId;
    }

    public String getCategoryStatusName() {
        return categoryStatusName;
    }

    public void setCategoryStatusName(String categoryStatusName) {
        this.categoryStatusName = categoryStatusName;
    }
}

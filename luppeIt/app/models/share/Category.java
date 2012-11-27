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
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category extends BaseModel {

    private Integer categoryId;
    private String categoryName;
    private Integer categoryStatusId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryStatusId() {
        return categoryStatusId;
    }

    public void setCategoryStatusId(Integer categoryStatusId) {
        this.categoryStatusId = categoryStatusId;
    }
}

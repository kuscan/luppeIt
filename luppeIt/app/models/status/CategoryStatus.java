package models.status;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryStatus extends Model {

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

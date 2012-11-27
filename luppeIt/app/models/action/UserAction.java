package models.action;

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
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserAction extends BaseModel {

    private Integer userActionId;
    private Integer actionId;
    private Integer userId;

    public Integer getUserActionId() {
        return userActionId;
    }

    public void setUserActionId(Integer userActionId) {
        this.userActionId = userActionId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

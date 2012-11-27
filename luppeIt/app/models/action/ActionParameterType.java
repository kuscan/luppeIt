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
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionParameterType extends BaseModel {

    private Integer actionParameterTypeId;
    private String actionParameterTypeName;
    private String actionParameterTypeClass;

    public Integer getActionParameterTypeId() {
        return actionParameterTypeId;
    }

    public void setActionParameterTypeId(Integer actionParameterTypeId) {
        this.actionParameterTypeId = actionParameterTypeId;
    }

    public String getActionParameterTypeName() {
        return actionParameterTypeName;
    }

    public void setActionParameterTypeName(String actionParameterTypeName) {
        this.actionParameterTypeName = actionParameterTypeName;
    }

    public String getActionParameterTypeClass() {
        return actionParameterTypeClass;
    }

    public void setActionParameterTypeClass(String actionParameterTypeClass) {
        this.actionParameterTypeClass = actionParameterTypeClass;
    }
}

package models.action;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionParameter extends Model {

    private Integer actionParameterId;
    private String actionParameterName;
    private Integer actionParameterTypeId;
    private Integer actionId;

    public Integer getActionParameterId() {
        return actionParameterId;
    }

    public void setActionParameterId(Integer actionParameterId) {
        this.actionParameterId = actionParameterId;
    }

    public String getActionParameterName() {
        return actionParameterName;
    }

    public void setActionParameterName(String actionParameterName) {
        this.actionParameterName = actionParameterName;
    }

    public Integer getActionParameterTypeId() {
        return actionParameterTypeId;
    }

    public void setActionParameterTypeId(Integer actionParameterTypeId) {
        this.actionParameterTypeId = actionParameterTypeId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
}

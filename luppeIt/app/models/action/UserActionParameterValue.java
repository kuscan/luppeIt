package models.action;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserActionParameterValue extends Model {

    private Integer userActionId;
    private Integer actionParameterId;
    private String parameterValue;

    public Integer getUserActionId() {
        return userActionId;
    }

    public void setUserActionId(Integer userActionId) {
        this.userActionId = userActionId;
    }

    public Integer getActionParameterId() {
        return actionParameterId;
    }

    public void setActionParameterId(Integer actionParameterId) {
        this.actionParameterId = actionParameterId;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}

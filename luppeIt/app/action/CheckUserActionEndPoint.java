package action;

import java.util.List;

import config.LuppeItConstants;
import database.dao.action.ActionDAO;
import database.dao.share.ShareDAO;

import play.Logger;

import exception.ProvisionException;

import models.action.ActionParameter;

public class CheckUserActionEndPoint implements ActionEndPointIF {

	private Integer actionId = null;
	private Integer userId = null;
	private Integer shareId = null;
	private Boolean undo = null;
	
	@Override
	public Boolean go() throws ProvisionException {
		/*
		 * Validate parameters
		 */
		if (actionId == null || userId == null || shareId == null) {
			throw new ProvisionException("00000", "CheckUserActionEndPoint cannot find required parameters for execution!");
		}
		
		/*
		 * Check if user action exists
		 */
		try {
			Integer actionParameterId = null;
			if (actionId == LuppeItConstants.ACTION_ID_LUPPE_SHARE) {
				actionParameterId = LuppeItConstants.LUPPE_SHARE_EP_SHARE_ID_PARAM;
			} else if (actionId == LuppeItConstants.ACTION_ID_DIG_SHARE) {
				actionParameterId = LuppeItConstants.DIG_SHARE_EP_SHARE_ID_PARAM;
			} else if (actionId == LuppeItConstants.ACTION_ID_VIEW_SHARE) {
				actionParameterId = LuppeItConstants.VIEW_SHARE_EP_SHARE_ID_PARAM;
			}
			Integer count = ActionDAO.checkIfUserActionExistsForShare(userId, actionId, actionParameterId, shareId.toString());
			
			if (count > 0) {
				if (undo != null && undo == true)
				{
					ActionDAO.deleteUserActionAndParameters(userId, actionId, actionParameterId, shareId.toString());
					if (actionId == LuppeItConstants.ACTION_ID_LUPPE_SHARE) {
						ShareDAO.updateShareLuppeCountDecreaseByOne(shareId);
					} else if (actionId == LuppeItConstants.ACTION_ID_DIG_SHARE) {
						ShareDAO.updateShareDigCountDecreaseByOne(shareId);
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (ProvisionException e) {
			throw e;
		}
	}

	public CheckUserActionEndPoint() { }
	
	public CheckUserActionEndPoint(Integer actionId, Integer userId, Integer shareId) {
		this.setActionId(actionId);
		this.setUserId(userId);
		this.setShareId(shareId);
	}
	
	public CheckUserActionEndPoint(Integer actionId, Integer userId, Integer shareId, Boolean undo) {
		this.setActionId(actionId);
		this.setUserId(userId);
		this.setShareId(shareId);
		this.setUndo(undo);
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

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public Boolean getUndo() {
		return undo;
	}

	public void setUndo(Boolean undo) {
		this.undo = undo;
	}
	
}

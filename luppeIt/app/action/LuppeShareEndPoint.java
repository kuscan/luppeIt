package action;

import java.util.List;

import config.LuppeItConstants;
import database.dao.action.ActionDAO;
import database.dao.share.ShareDAO;

import play.Logger;

import exception.ProvisionException;

import models.action.ActionParameter;

public class LuppeShareEndPoint implements ActionEndPointIF {

	private Integer userId = null;
	private Integer shareId = null;
	
	@Override
	public Boolean go() throws ProvisionException {
		/*
		 * Validate parameters
		 */
		if (userId == null || shareId == null) {
			throw new ProvisionException("00000", "LuppeShareEndPoint cannot find required parameters for execution!");
		}
		
		/*
		 * Add user action and parameter values
		 */
		try {
			Long userActionId = ActionDAO.addUserAction(LuppeItConstants.ACTION_ID_LUPPE_SHARE, userId);
			ActionDAO.addUserActionParameterValue(userActionId, LuppeItConstants.LUPPE_SHARE_EP_USER_ID_PARAM, userId.toString());
			ActionDAO.addUserActionParameterValue(userActionId, LuppeItConstants.LUPPE_SHARE_EP_SHARE_ID_PARAM, shareId.toString());
			ShareDAO.updateShareLuppeCountIncreaseByOne(shareId);
			return true;
		} catch (ProvisionException e) {
			return false;
		}
	}

	public LuppeShareEndPoint() { }
	
	public LuppeShareEndPoint(Integer userId, Integer shareId) {
		this.setUserId(userId);
		this.setShareId(shareId);
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
	
}

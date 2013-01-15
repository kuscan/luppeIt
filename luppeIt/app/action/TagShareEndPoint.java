package action;

import java.util.List;

import config.LuppeItConstants;
import database.dao.action.ActionDAO;
import database.dao.share.ShareDAO;
import database.dao.tag.TagDAO;

import play.Logger;

import exception.ProvisionException;

import models.action.ActionParameter;

public class TagShareEndPoint implements ActionEndPointIF {

	private Integer userId = null;
	private Integer tagId = null;
	
	@Override
	public Boolean go() throws ProvisionException {
		/*
		 * Validate parameters
		 */
		if (userId == null || tagId == null) {
			throw new ProvisionException("00000", "TagShareEndPoint cannot find required parameters for execution!");
		}
		
		/*
		 * Add user action and parameter values
		 */
		try {
			Long userActionId = ActionDAO.addUserAction(LuppeItConstants.ACTION_ID_TAG_SHARE, userId);
			ActionDAO.addUserActionParameterValue(userActionId, LuppeItConstants.TAG_SHARE_EP_USER_ID_PARAM, userId.toString());
			ActionDAO.addUserActionParameterValue(userActionId, LuppeItConstants.TAG_SHARE_EP_TAG_ID_PARAM, tagId.toString());
			return true;
		} catch (ProvisionException e) {
			return false;
		}
	}

	public TagShareEndPoint() { }
	
	public TagShareEndPoint(Integer userId, Integer tagId) {
		this.setUserId(userId);
		this.setTagId(tagId);
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
}

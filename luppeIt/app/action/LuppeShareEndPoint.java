package action;

import java.util.List;

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
		
		
		
		return false;
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

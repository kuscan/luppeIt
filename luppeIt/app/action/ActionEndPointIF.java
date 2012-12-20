package action;

import java.util.List;

import exception.ProvisionException;

import models.action.ActionParameter;

public interface ActionEndPointIF {

	public Boolean go() throws ProvisionException;
	
}

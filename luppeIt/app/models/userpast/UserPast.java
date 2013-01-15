package models.userpast;

import java.util.List;

import models.BaseModel;

public class UserPast extends BaseModel {
	
	private List<UserPastUnit> addedTags;
	private List<UserPastUnit> luppedTags;
	private List<UserPastUnit> viewedTags;
	private List<UserPastUnit> diggedTags;
	private List<UserPastUnit> luppedResources;
	private List<UserPastUnit> viewedResources;
	private List<UserPastUnit> diggedResources;
	
	public List<UserPastUnit> getAddedTags() {
		return addedTags;
	}
	
	public void setAddedTags(List<UserPastUnit> addedTags) {
		this.addedTags = addedTags;
	}
	
	public List<UserPastUnit> getLuppedTags() {
		return luppedTags;
	}
	
	public void setLuppedTags(List<UserPastUnit> luppedTags) {
		this.luppedTags = luppedTags;
	}
	
	public List<UserPastUnit> getViewedTags() {
		return viewedTags;
	}
	
	public void setViewedTags(List<UserPastUnit> viewedTags) {
		this.viewedTags = viewedTags;
	}
	
	public List<UserPastUnit> getDiggedTags() {
		return diggedTags;
	}
	
	public void setDiggedTags(List<UserPastUnit> diggedTags) {
		this.diggedTags = diggedTags;
	}
	
	public List<UserPastUnit> getLuppedResources() {
		return luppedResources;
	}
	
	public void setLuppedResources(List<UserPastUnit> luppedResources) {
		this.luppedResources = luppedResources;
	}
	
	public List<UserPastUnit> getViewedResources() {
		return viewedResources;
	}
	
	public void setViewedResources(List<UserPastUnit> viewedResources) {
		this.viewedResources = viewedResources;
	}
	
	public List<UserPastUnit> getDiggedResources() {
		return diggedResources;
	}
	
	public void setDiggedResources(List<UserPastUnit> diggedResources) {
		this.diggedResources = diggedResources;
	}
}

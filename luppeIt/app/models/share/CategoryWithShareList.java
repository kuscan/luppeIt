package models.share;

import java.util.List;

import models.BaseModel;

public class CategoryWithShareList extends BaseModel {

	private Category category;
	private List<Share> shares;
	
	public CategoryWithShareList() {
		
	}
	
	public CategoryWithShareList(Category category, List<Share> shares) {
		this.category = category;
		this.shares = shares;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<Share> getShares() {
		return shares;
	}
	
	public void setShares(List<Share> shares) {
		this.shares = shares;
	}
	
}

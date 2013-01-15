package models.userpast;

import models.BaseModel;

public class UserPastUnit extends BaseModel {

	private Integer id;
	private Integer amount;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}

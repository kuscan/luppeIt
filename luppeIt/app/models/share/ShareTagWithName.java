package models.share;

public class ShareTagWithName {
	
	private Integer shareTagId;
	private Integer shareId;
	private Integer tagId;
	private Integer truth;
	private String tagName;
	
	public Integer getShareTagId() {
		return shareTagId;
	}
	
	public void setShareTagId(Integer shareTagId) {
		this.shareTagId = shareTagId;
	}
	
	public Integer getShareId() {
		return shareId;
	}
	
	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	
	public Integer getTagId() {
		return tagId;
	}
	
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	public Integer getTruth() {
		return truth;
	}
	
	public void setTruth(Integer truth) {
		this.truth = truth;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}

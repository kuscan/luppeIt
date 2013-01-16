package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;

import database.dao.share.ShareDAO;
import database.dao.tag.TagDAO;

import models.share.Share;
import models.userpast.UserPast;
import models.userpast.UserPastUnit;

public class Recommender {
	
	public static List<Share> recommend(List<Share> shares, UserPast userPast) {
		Map<Integer, List<Integer>> shareTags = new HashMap<Integer, List<Integer>>();
		Map<Integer, Integer> shareResources = new HashMap<Integer, Integer>();
		Map<Integer, Integer> sharePoints = new HashMap<Integer, Integer>();
		
		shareTags = TagDAO.getTagsOfShares(shares);
		shareResources = ShareDAO.getResourcesOfShares(shares);
		
		for (Share share: shares) {
			Logger.info("Share puanlama basladi: " + share.getShareId().toString());
			Integer point = 0;
			
			if (shareTags.get(share.getShareId()) != null) {
				//Added tags
				for (UserPastUnit addedTag: userPast.getAddedTags()) {
					if (checkContains(shareTags.get(share.getShareId()), addedTag.getId())) {
						point += 5 * addedTag.getAmount();
					}
				}
				
				//lupped share's tags
				for (UserPastUnit luppedTag: userPast.getLuppedTags()) {
					if (checkContains(shareTags.get(share.getShareId()), luppedTag.getId())) {
						point += 4 * luppedTag.getAmount();
					}
				}
				
				//viewed share's tags
				for (UserPastUnit viewedTag: userPast.getViewedTags()) {
					if (checkContains(shareTags.get(share.getShareId()), viewedTag.getId())) {
						point += 2 * viewedTag.getAmount();
					}
				}
				
				//digged share's tags
				for (UserPastUnit diggedTag: userPast.getDiggedTags()) {
					if (checkContains(shareTags.get(share.getShareId()), diggedTag.getId())) {
						point += -4 * diggedTag.getAmount();
					}
				}
			}
			
			if (shareResources.get(share.getShareId()) != null) {
				//lupped share's resource
				for (UserPastUnit luppedResource: userPast.getLuppedResources()) {
					if (shareResources.get(share.getShareId()).intValue() == luppedResource.getId()) {
						point += 3 * luppedResource.getAmount();
					}
				}
				
				//viewed share's resource
				for (UserPastUnit viewedResource: userPast.getViewedResources()) {
					if (shareResources.get(share.getShareId()).intValue() == viewedResource.getId()) {
						point += 1 * viewedResource.getAmount();
					}
				}
				
				//digged share's resource
				for (UserPastUnit diggedResource: userPast.getDiggedResources()) {
					if (shareResources.get(share.getShareId()).intValue() == diggedResource.getId()) {
						point += -2 * diggedResource.getAmount();
					}
				}
			}
			
			
			
			sharePoints.put(share.getShareId(), point);
			
			
		}
		
		for (int i = 0; i < shares.size(); i++) {
			for (int j = i + 1; j < shares.size(); j++) {
				if (sharePoints.get(shares.get(i).getShareId()) < sharePoints.get(shares.get(j).getShareId())) {
					Collections.swap(shares, i, j); 
				}
			}
		}
		
		
		return shares;
	}
	
	private static Boolean checkContains(List<Integer> list, Integer amount) {
		for (Integer item: list) {
			Logger.info(item + " " + amount);
			if (item.intValue() == amount.intValue()) {
				return true;
			}
		}
		return false;
	}
}

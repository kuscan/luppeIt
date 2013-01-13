package controllers.ajax;

import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.category.CategoryDAO;
import database.dao.share.ShareDAO;
import database.dao.tag.TagDAO;
import models.share.Category;
import models.share.Share;
import models.share.Tag;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;
import utils.RssReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AjaxController extends BaseController {

	public static void autocompleteTag(String text) {
		List<String> tags = TagDAO.getCorrespondingTags(text);
		renderJSON(tags);
    }
	
	public static void confirmShareTag(Integer shareTagId) {
    	if (TagDAO.updateShareTagIncreaseTruthByOne(shareTagId)) {
    		renderText("Success");
    	} else {
    		renderText("Failure");
    	}
    }
    
    public static void denyShareTag(Integer shareTagId) {
    	if (TagDAO.updateShareTagDecreaseTruthByOne(shareTagId)) {
    		renderText("Success");
    	} else {
    		renderText("Failure");
    	}
    }
	
}
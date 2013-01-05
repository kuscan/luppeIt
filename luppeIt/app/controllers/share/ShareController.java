package controllers.share;

import java.util.HashMap;

import action.CheckUserActionEndPoint;
import action.DigShareEndPoint;
import action.LuppeShareEndPoint;
import action.ViewShareEndPoint;

import models.share.Share;

import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.share.ShareDAO;
import exception.ProvisionException;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShareController extends BaseController {

    @Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void beforeShareController() {
        if (checkLogin()) {
            renderArgs.put("user", Cache.get("user" + session.get("userId")));
        } else {
        	
        }
    }
    
    public static void share(Integer shareId) {
    	/*
    	 * view share action
    	 */
    	viewShare(shareId);
    	if (checkLogin()) {
    		try {
		    	Integer userId = Integer.parseInt(session.get("userId"));
		    	
		    	CheckUserActionEndPoint ep = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_LUPPE_SHARE, userId, shareId);
				renderArgs.put("isLupped", ep.go());
				
				ep = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_DIG_SHARE, userId, shareId);
				renderArgs.put("isDigged", ep.go());
			} catch (ProvisionException e) {
				Logger.error(e, "ProvisionException caught!");
			}
    	}
    	
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	Share share = ShareDAO.getShareByShareId(shareId);
    	if (share == null) {
    		renderTemplate(NavigationConstants.shareNotFoundPage, arguments);
    	} else {
    		renderArgs.put("share", share);
    	}
    	
    	renderTemplate(NavigationConstants.sharePage, arguments);
    }
    
    public static void originalShare(Integer shareId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	Share share = ShareDAO.getShareByShareId(shareId);
    	if (share == null) {
    		renderTemplate(NavigationConstants.shareNotFoundPage, arguments);
    	} else {
    		renderArgs.put("share", share);
    	}
    	
    	renderTemplate(NavigationConstants.originalSharePage, arguments);
    }
    
    private static void viewShare(Integer shareId) {
    	if (session.contains("userId")) {
            try {
            	ViewShareEndPoint ep = new ViewShareEndPoint(Integer.parseInt(session.get("userId")), shareId);
				ep.go();
			} catch (ProvisionException e) {
				e.printStackTrace();
				render(NavigationConstants.errorPage500);
			}
        } else {
        	try {
            	ViewShareEndPoint ep = new ViewShareEndPoint(LuppeItConstants.DEFAULT_USER_ID, shareId);
				ep.go();
			} catch (ProvisionException e) {
				e.printStackTrace();
				render(NavigationConstants.errorPage500);
			}
        }
    }
    
    public static void luppeShare(Integer shareId) {
    	if (checkLogin()) {
            try {
            	Integer userId = Integer.parseInt(session.get("userId"));
            	
            	/*
            	 * If user did not luppe the share before, luppe it. Otherwise return failure.
            	 */
            	CheckUserActionEndPoint checkUserActionEndPoint = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_LUPPE_SHARE, userId, shareId);
            	if (!checkUserActionEndPoint.go()) {
            		
            		/*
            		 * If user digged the share before, undo it. Then luppe the share.
            		 */
            		checkUserActionEndPoint = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_DIG_SHARE, userId, shareId, true);
            		checkUserActionEndPoint.go();
            		
	            	LuppeShareEndPoint ep = new LuppeShareEndPoint(userId, shareId);
					ep.go();
					renderText("Success");
            	}
			} catch (ProvisionException e) {
				e.printStackTrace();
				renderText("Failure");
			}
        } else {
        	renderText("Failure");
        }
    }
    
    public static void digShare(Integer shareId) {
    	if (checkLogin()) {
            try {
            	Integer userId = Integer.parseInt(session.get("userId"));
            	
            	/*
            	 * If user did not dig the share before, dig it. Otherwise return failure.
            	 */
            	CheckUserActionEndPoint checkUserActionEndPoint = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_DIG_SHARE, userId, shareId);
            	if (!checkUserActionEndPoint.go()) {
            		
            		/*
            		 * If user lupped the share before, undo it. Then dig the share.
            		 */
            		checkUserActionEndPoint = new CheckUserActionEndPoint(LuppeItConstants.ACTION_ID_LUPPE_SHARE, userId, shareId, true);
            		checkUserActionEndPoint.go();
            		
	            	DigShareEndPoint ep = new DigShareEndPoint(Integer.parseInt(session.get("userId")), shareId);
					ep.go();
					renderText("Success");
            	}
			} catch (ProvisionException e) {
				e.printStackTrace();
				renderText("Failure");
			}
        } else {
        	renderText("Failure");
        }
    }


}

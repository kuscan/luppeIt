package controllers;

import config.LuppeItConstants;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;

public class BaseController extends Controller {

	public static Integer getUserId() {
		return Integer.parseInt(session.get("userId"));
	}
	
	public static Boolean checkLogin() {
		if (session.contains("userId") && Cache.get("user" + session.get("userId")) != null) {
			Logger.info("User is logged in! User id: " + session.get("userId"));
			return true;
		}
		Logger.info("User is not logged in!");
		return false;
	}
	
	public static Boolean checkAdmin() {
		if (session.contains("userId") 
				&& Cache.get("user" + session.get("userId")) != null 
                && session.contains("userType") 
                && session.get("userType").equals(LuppeItConstants.USER_TYPE_ID_ADMIN.toString())) {
			Logger.info("Admin is logged in! User id: " + session.get("userId"));
            return true;
        }
		Logger.info("Admin is not logged in!");
		return false;
	}
	
}

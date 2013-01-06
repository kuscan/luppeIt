package controllers.profile;

import java.util.HashMap;
import java.util.List;

import models.user.Country;
import models.user.User;

import play.cache.Cache;
import play.mvc.Before;
import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.country.CountryDAO;

public class ProfileController extends BaseController {

	@Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void beforeProfileController() {
        if (checkLogin()) {
            renderArgs.put("user", Cache.get("user" + session.get("userId")));
        } else {
        	redirect(LuppeItConstants.BASE_URL);
        }
    }
    
    public static void profile() {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	List<Country> countries = CountryDAO.getAllCountriesOrderedByName();
    	renderArgs.put("countries", countries);
    	
    	renderTemplate(NavigationConstants.profilePage, arguments);
    }
	
    public static void updateProfile() {
    	
    }
}

package controllers.profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.share.Category;
import models.user.Country;
import models.user.User;

import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.category.CategoryDAO;
import database.dao.country.CountryDAO;
import database.dao.user.UserDAO;

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
    	
    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
    	List<Category> userInterests = CategoryDAO.getActiveUserCategories(getUserId());
    	List<Integer> interests = new ArrayList<Integer>();
    	for (Category userInterest: userInterests) {
    		interests.add(userInterest.getCategoryId());
    	}
    	renderArgs.put("interests", interests);
    	
    	renderTemplate(NavigationConstants.profilePage, arguments);
    }
	
    public static void updateProfile(String username, String password, Integer age, Integer country) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
		
		List<Country> countries = CountryDAO.getAllCountriesOrderedByName();
    	renderArgs.put("countries", countries);
    	
    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
    	List<Category> userInterests = CategoryDAO.getActiveUserCategories(getUserId());
    	List<Integer> interests = new ArrayList<Integer>();
    	for (Category userInterest: userInterests) {
    		interests.add(userInterest.getCategoryId());
    	}
    	renderArgs.put("interests", interests);
    	
    	if (username == null || username.isEmpty() || password == null || password.length() < 6 || age == null || age < 0 || country == null || country < 0) {
    		arguments.put("updateError", "There are invalid or missing fields!");
    		renderTemplate(NavigationConstants.profilePage, arguments);
    	}
    	
    	try {
	    	User user = Cache.get("user" + getUserId().toString(), User.class);
	    	user.setUsername(username);
	    	user.setPassword(password);
	    	user.setAge(age);
	    	user.setCountryId(country);
	    	UserDAO.updateUser(user);
	    	arguments.put("updateError", "Profile information successfully updated.");
    	} catch (Exception e) {
    		arguments.put("updateError", "Profile information cannot be updated!");
    	}
    	
    	renderTemplate(NavigationConstants.profilePage, arguments);
    }
    
    public static void updateUserCategories(List<Integer> userCategories) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
		
		List<Country> countries = CountryDAO.getAllCountriesOrderedByName();
    	renderArgs.put("countries", countries);
    	
    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
    	List<Category> userInterests = new ArrayList<Category>();
    	List<Integer> interests = new ArrayList<Integer>();
    	
    	if (userCategories == null || userCategories.size() == 0) {
    		userInterests = CategoryDAO.getActiveUserCategories(getUserId());
    		
        	for (Category userInterest: userInterests) {
        		interests.add(userInterest.getCategoryId());
        	}
        	renderArgs.put("interests", interests);
    		
    		arguments.put("userCategoryError", "You must select at least one category!");
    		renderTemplate(NavigationConstants.profilePage, arguments);
    	}
    	
    	try {
    		CategoryDAO.updateUserCategories(Long.parseLong(getUserId().toString()), userCategories);
    		
    		userInterests = CategoryDAO.getActiveUserCategories(getUserId());
    		
        	for (Category userInterest: userInterests) {
        		interests.add(userInterest.getCategoryId());
        	}
        	renderArgs.put("interests", interests);
    		
    		arguments.put("userCategoryError", "User categories successfully updated.");
    		renderTemplate(NavigationConstants.profilePage, arguments);
    	} catch (Exception e) {
    		userInterests = CategoryDAO.getActiveUserCategories(getUserId());
    		
        	for (Category userInterest: userInterests) {
        		interests.add(userInterest.getCategoryId());
        	}
        	renderArgs.put("interests", interests);
    		
    		arguments.put("userCategoryError", "User categories can not be updated!");
    		renderTemplate(NavigationConstants.profilePage, arguments);
    	}
    	
    }
}

package controllers.category;

import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.category.CategoryDAO;
import database.dao.share.ShareDAO;
import models.share.Category;
import models.share.CategoryWithShareList;
import models.share.Share;
import models.user.User;
import models.userpast.UserPast;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;
import utils.Recommender;
import utils.RssReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryController extends BaseController {

    @Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void beforeCategoryController() {
        if (checkLogin()) {
            renderArgs.put("user", Cache.get("user" + session.get("userId")));
        } else {
        }
    }

    public static void categories() {
    	HashMap<String, String> arguments = new HashMap<String, String>();

    	List<Category> categories = null;
    	if (checkLogin()) {
    		categories = CategoryDAO.getActiveUserCategories(getUserId());
    	} else {
    		categories = CategoryDAO.getAllCategoriesOrderByName();
    	}
    	renderArgs.put("categories", categories);
    	
    	List<CategoryWithShareList> categoriesWithShares = new ArrayList<CategoryWithShareList>();
    	for (Category category: categories) {
	    	if (checkLogin()) {
	    		
	    		List<Share> topNews = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdTopNewsForRegisteredUser(category.getCategoryId());
	    		categoriesWithShares.add(new CategoryWithShareList(category, Recommender.recommend(topNews, Cache.get("userPast" + getUserId().toString(), UserPast.class))));
	    		
	    	} else {
	    		
	    		List<Share> topNews = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdTopNews(category.getCategoryId());
	    		categoriesWithShares.add(new CategoryWithShareList(category, topNews));
	    		
	    	}
    	}
    	renderArgs.put("categoriesWithShares", categoriesWithShares);
    	
        renderTemplate(NavigationConstants.categoriesPage, arguments);
    }
    
    public static void category(Integer categoryId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	Category category = CategoryDAO.getCategoryByCategoryId(categoryId);
    	renderArgs.put("category", category);
    	
    	List<Share> topNews = null;
    	List<Share> mostRecents = null;
    	if (checkLogin()) {
    		topNews = Recommender.recommend(ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdTopNewsForRegisteredUser(categoryId), Cache.get("userPast" + getUserId().toString(), UserPast.class));
    		mostRecents = Recommender.recommend(ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdMostRecentForRegisteredUser(categoryId), Cache.get("userPast" + getUserId().toString(), UserPast.class));
    	} else {
    		topNews = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdTopNews(categoryId);
    		mostRecents = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryIdMostRecent(categoryId);
    	}
    	renderArgs.put("topNews", topNews);
    	renderArgs.put("mostRecents", mostRecents);
    	
    	List<Category> categories = null;
    	if (checkLogin()) {
    		categories = CategoryDAO.getActiveUserCategories(getUserId());
    	} else {
    		categories = CategoryDAO.getAllCategoriesOrderByName();
    	}
    	renderArgs.put("categories", categories);
    	       
        renderTemplate(NavigationConstants.categoryPage, arguments);
    }
}
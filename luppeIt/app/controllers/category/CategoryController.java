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
    		List<Share> shares = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryId(category.getCategoryId());
    		categoriesWithShares.add(new CategoryWithShareList(category, shares));
    	}
    	renderArgs.put("categoriesWithShares", categoriesWithShares);
        
        renderTemplate(NavigationConstants.categoriesPage, arguments);
    }
    
    public static void category(Integer categoryId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	Category category = CategoryDAO.getCategoryByCategoryId(categoryId);
    	renderArgs.put("category", category);

    	List<Share> topNews = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryId(categoryId);
    	renderArgs.put("topNews", topNews);
    	
    	List<Share> mostRecents = ShareDAO.getSharesOfLastWeekWithDetailsByCategoryId(categoryId);
    	renderArgs.put("mostRecents", mostRecents);
    	       
        renderTemplate(NavigationConstants.categoryPage, arguments);
    }
}
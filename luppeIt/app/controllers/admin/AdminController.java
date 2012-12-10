package controllers.admin;

import java.util.HashMap;
import java.util.List;

import models.share.Resource;
import models.status.ResourceStatus;
import models.user.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import config.LuppeItConstants;
import config.NavigationConstants;
import database.dao.resource.ResourceDAO;
import database.dao.rssresource.RssResourceDAO;
import database.dao.user.UserDAO;

public class AdminController extends Controller {

	@Before
    static void before() {
    	Logger.info("here");
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }
    
    @Before(unless = {"login", "completeLogin"})
    static void checkAdmin() {
    	Logger.info("here2");
        if (session.contains("userId") 
                && session.contains("userType") 
                && session.get("userType").equals(LuppeItConstants.USER_TYPE_ID_ADMIN.toString())) {
            renderArgs.put("user", Cache.get("user" + session.get("userId")));
        } else {
            redirect(LuppeItConstants.BASE_URL + "/admin/login");
        }
    }
    
    public static void login() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminLoginPage, arguments);
    }
    
    public static void completeLogin(String email, String password) {
        HashMap<String, String> arguments = new HashMap<String, String>();

        Boolean hasError = false;
        if (email == null || email.equals("")) {
            arguments.put("emailRequiredError", LuppeItConstants.EMAIL_REQUIRED_MESSAGE);
            hasError = true;
        }
        if (password == null || password.equals("")) {
            arguments.put("passwordRequiredError", LuppeItConstants.PASSWORD_REQUIRED_MESSAGE);
            hasError = true;
        }
        if (hasError) {
            renderTemplate(NavigationConstants.adminLoginPage, arguments);
        }


        User user = UserDAO.getUserByEmailAndPassword(email, password);
        if (user == null) {
            arguments.put("loginError", LuppeItConstants.WRONG_USERNAME_PASSWORD_ERROR_MESSAGE);
            hasError = true;
        } else if (user.getUserStatusId() == LuppeItConstants.USER_STATUS_ID_REGISTERED) {
            arguments.put("loginError", LuppeItConstants.EMAIL_NOT_CONFIRMED_ERROR_MESSAGE);
            hasError = true;
        } else if (user.getUserTypeId() != LuppeItConstants.USER_TYPE_ID_ADMIN) {
            arguments.put("loginError", LuppeItConstants.USER_NOT_AUTHENTICATED_ERROR_MESSAGE);
            hasError = true;
        }
        if (hasError) {
            renderTemplate(NavigationConstants.adminLoginPage, arguments);
        }


        if (user.getUserStatusId() == LuppeItConstants.USER_STATUS_ID_ACTIVE) {
            session.put("userId", user.getUserId());
            session.put("userType", LuppeItConstants.USER_TYPE_ID_ADMIN.toString());
            Cache.set("user" + user.getUserId().toString(), user, "60mn");
            redirect(LuppeItConstants.BASE_URL + "/admin");
        }
    }
    
    public static void logout() {
    	
    }
    
    public static void index() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminHomePage, arguments);
    }
    
    public static void resources() {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	List<Resource> resources = ResourceDAO.getAllResources();
    	renderArgs.put("resources", resources);
    	
    	List<ResourceStatus> resourceStatuses = ResourceDAO.getAllResourceStatuses();
    	renderArgs.put("resourceStatuses", resourceStatuses);
    	
        renderTemplate(NavigationConstants.adminResourcesPage, arguments);
    }
    
    public static void resource(Integer resourceId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminResourcePage, arguments);
    }
    
    public static void addResource() {
    	
    }
    
    public static void rssResources() {
    	HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminRssResourcesPage, arguments);
    }
    
    public static void rssResource(Integer rssResourceId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminRssResourcePage, arguments);
    }
    
    public static void categories() {
    	HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminCategoriesPage, arguments);
    }
    
    public static void category(Integer categoryId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.adminCategoryPage, arguments);
    }
	
}

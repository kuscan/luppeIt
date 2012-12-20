package controllers.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import models.share.Category;
import models.share.Resource;
import models.share.RssResource;
import models.status.CategoryStatus;
import models.status.ResourceStatus;
import models.status.RssResourceStatus;
import models.user.User;
import play.Logger;
import play.cache.Cache;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Controller;
import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.category.CategoryDAO;
import database.dao.resource.ResourceDAO;
import database.dao.rssresource.RssResourceDAO;
import database.dao.user.UserDAO;

public class AdminController extends BaseController {

	@Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }
    
    @Before(unless = {"login", "completeLogin"})
    static void beforeAdminController() {
        if (checkAdmin()) {
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
    	Cache.delete("user" + session.get("userId"));
        session.clear();

        redirect(LuppeItConstants.BASE_URL);
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
    	
    	Resource resource = ResourceDAO.getResourceByResourceId(resourceId);
    	renderArgs.put("resource", resource);
    	
    	List<ResourceStatus> resourceStatuses = ResourceDAO.getAllResourceStatuses();
    	renderArgs.put("resourceStatuses", resourceStatuses);

        renderTemplate(NavigationConstants.adminResourcePage, arguments);
    }
    
    public static void addResource(String resourceName, Integer resourceStatusId) {
    	Resource resource = new Resource();
	    resource.setResourceName(resourceName);
	    resource.setResourceStatusId(resourceStatusId);
	    
	    ResourceDAO.addResource(resource);
	    redirect(LuppeItConstants.BASE_URL + "/admin/resources");
    }
    
    public static void updateResource(Integer resourceId, String resourceName, Integer resourceStatusId) {
    	Resource resource = new Resource();
    	resource.setResourceId(resourceId);
    	resource.setResourceName(resourceName);
    	resource.setResourceStatusId(resourceStatusId);
    	
    	ResourceDAO.updateResource(resource);
    	redirect(LuppeItConstants.BASE_URL + "/admin/resource/" + resourceId.toString());
    }
    
    public static void deleteResource(Integer resourceId) {
    	ResourceDAO.deleteResource(resourceId);
    	redirect(LuppeItConstants.BASE_URL + "/admin/resources");
    }
    
    public static void rssResources() {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	List<RssResource> rssResources = RssResourceDAO.getAllRssResources();
    	renderArgs.put("rssResources", rssResources);
    	
    	List<Resource> resources = ResourceDAO.getAllResources();
    	renderArgs.put("resources", resources);
    	
    	List<RssResourceStatus> rssResourceStatuses = RssResourceDAO.getAllRssResourceStatuses();
    	renderArgs.put("rssResourceStatuses", rssResourceStatuses);
    	
    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
        renderTemplate(NavigationConstants.adminRssResourcesPage, arguments);
    }
    
    public static void rssResource(Integer rssResourceId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();

    	RssResource rssResource = RssResourceDAO.getRssResourceByRssResourceId(rssResourceId);
    	renderArgs.put("rssResource", rssResource);
    	
    	List<Resource> resources = ResourceDAO.getAllResources();
    	renderArgs.put("resources", resources);
    	
    	List<RssResourceStatus> rssResourceStatuses = RssResourceDAO.getAllRssResourceStatuses();
    	renderArgs.put("rssResourceStatuses", rssResourceStatuses);
    	
    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
        renderTemplate(NavigationConstants.adminRssResourcePage, arguments);
    }
    
    public static void addRssResource(String rssResourceName, String url, Integer parentResourceId, Integer rssResourceStatusId, Integer categoryId, Integer updateIntervalMinute) {
    	RssResource rssResource = new RssResource();
    	rssResource.setRssResourceName(rssResourceName);
    	rssResource.setUrl(url);
    	rssResource.setParentResourceId(parentResourceId);
    	rssResource.setRssResourceStatusId(rssResourceStatusId);
    	rssResource.setCategoryId(categoryId);
    	rssResource.setUpdateIntervalMinute(updateIntervalMinute);
    	rssResource.setNextFeedDate(Calendar.getInstance().getTimeInMillis());
    	
    	RssResourceDAO.addRssResource(rssResource);
    	redirect(LuppeItConstants.BASE_URL + "/admin/rssResources");
    }
    
    public static void updateRssResource(Integer rssResourceId, String rssResourceName, String url, Integer parentResourceId, Integer rssResourceStatusId, Integer categoryId, Integer updateIntervalMinute) {
    	RssResource rssResource = new RssResource();
    	rssResource.setRssResourceId(rssResourceId);
    	rssResource.setRssResourceName(rssResourceName);
    	rssResource.setUrl(url);
    	rssResource.setParentResourceId(parentResourceId);
    	rssResource.setRssResourceStatusId(rssResourceStatusId);
    	rssResource.setCategoryId(categoryId);
    	rssResource.setUpdateIntervalMinute(updateIntervalMinute);
    	rssResource.setNextFeedDate(Calendar.getInstance().getTimeInMillis());
    	
    	RssResourceDAO.updateRssResource(rssResource);
    	redirect(LuppeItConstants.BASE_URL + "/admin/rssResource/" + rssResourceId.toString());
    }
    
    public static void deleteRssResource(Integer rssResourceId) {
    	RssResourceDAO.deleteRssResource(rssResourceId);
    	redirect(LuppeItConstants.BASE_URL + "/admin/rssResources");
    }
    
    public static void categories() {
    	HashMap<String, String> arguments = new HashMap<String, String>();

    	List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
    	renderArgs.put("categories", categories);
    	
    	List<CategoryStatus> categoryStatuses = CategoryDAO.getAllCategoryStatuses();
    	renderArgs.put("categoryStatuses", categoryStatuses);
    	
        renderTemplate(NavigationConstants.adminCategoriesPage, arguments);
    }
    
    public static void category(Integer categoryId) {
    	HashMap<String, String> arguments = new HashMap<String, String>();
    	
    	Category category = CategoryDAO.getCategoryByCategoryId(categoryId);
    	renderArgs.put("category", category);
    	
    	List<CategoryStatus> categoryStatuses = CategoryDAO.getAllCategoryStatuses();
    	renderArgs.put("categoryStatuses", categoryStatuses);
    	
        renderTemplate(NavigationConstants.adminCategoryPage, arguments);
    }
    
    public static void addCategory(String categoryName, Integer categoryStatusId) {
    	Category category = new Category();
    	category.setCategoryName(categoryName);
    	category.setCategoryStatusId(categoryStatusId);
    	
    	CategoryDAO.addCategory(category);
    	redirect(LuppeItConstants.BASE_URL + "/admin/categories");
    }
    
    public static void updateCategory(Integer categoryId, String categoryName, Integer categoryStatusId) {
    	Category category = new Category();
    	category.setCategoryId(categoryId);
    	category.setCategoryName(categoryName);
    	category.setCategoryStatusId(categoryStatusId);
    	
    	CategoryDAO.updateCategory(category);
    	redirect(LuppeItConstants.BASE_URL + "/admin/category/" + categoryId.toString());
    }
    
    public static void deleteCategory(Integer categoryId) {
    	CategoryDAO.deleteCategory(categoryId);
    	redirect(LuppeItConstants.BASE_URL + "/admin/categories");
    }
    
}

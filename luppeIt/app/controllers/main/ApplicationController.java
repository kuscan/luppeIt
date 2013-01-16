package controllers.main;

import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.category.CategoryDAO;
import database.dao.share.ShareDAO;
import models.share.Category;
import models.share.Share;
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

public class ApplicationController extends BaseController {

    @Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void beforeApplicationController() {
        if (checkLogin()) {
            renderArgs.put("user", Cache.get("user" + session.get("userId")));
        } else {
        }
    }

    public static void index() {
        /*
            If a user is logged in, go to homepage method
         */
        if (session.contains("userId")) {
            homepage();
        }

        HashMap<String, String> arguments = new HashMap<String, String>();

        List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
        renderArgs.put("categories", categories);

        List<Share> mostRecents = ShareDAO.getMostRecent();
        renderArgs.put("mostRecents", mostRecents);

        List<Share> topNews = ShareDAO.getTopNews();
        renderArgs.put("topNews", topNews);

        renderTemplate(NavigationConstants.mainPage, arguments);
    }

    static void homepage() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        List<Category> categories = CategoryDAO.getActiveUserCategories(Integer.parseInt(session.get("userId")));
        renderArgs.put("categories", categories);
        
        List<Share> mostRecents = ShareDAO.getMostRecentForRegisteredUser().subList(0, 100);
        renderArgs.put("mostRecents", mostRecents);

        List<Share> topNews = ShareDAO.getTopNewsForRegisteredUser().subList(0, 100);
        renderArgs.put("topNews", Recommender.recommend(topNews, Cache.get("userPast" + session.get("userId"), UserPast.class)));

        renderTemplate(NavigationConstants.homePage, arguments);
    }
}
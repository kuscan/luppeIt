package controllers.main;

import config.LuppeItConstants;
import config.NavigationConstants;
import database.dao.category.CategoryDAO;
import database.dao.share.ShareDAO;
import models.share.Category;
import models.share.Share;
import models.user.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import utils.RssReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ApplicationController extends Controller {

    @Before
    static void before() {
        try {
            RssReader.readRssFeed("http://www.npr.org/rss/rss.php?id=1014");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.info(LuppeItConstants.BASE_URL);
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void checkLogin() {
        User currentUser = (User) Cache.get("currentUser");
        if (currentUser != null) {
            Logger.info("User " + currentUser.getUsername() + " is logged in!");
            renderArgs.put("currentUser", currentUser);
        } else {
            Logger.info("User is not logged in!");
        }
    }

    public static void index() {
        /*
            If a user is logged in, go to homepage method
         */
        if (Cache.get("currentUser") != null) {
            homepage();
        }

        /*
            If user is anonymous, continue from here
         */
        HashMap<String, String> arguments = new HashMap<String, String>();

        /*
            Get all categories and inject into view
         */
        List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
        renderArgs.put("categories", categories);

        /*
            Get top news and inject into view
         */
        //List<Share> topNews = Share.find("")

        /*
            Get most recent news and inject into view
         */
        List<Share> mostRecents = ShareDAO.getMostRecent();
        renderArgs.put("mostRecents", mostRecents);



        renderTemplate(NavigationConstants.mainPage, arguments);
    }

    static void homepage() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        /*
            Get all categories and inject into view
         */
        List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();
        renderArgs.put("categories", categories);

        /*
            Get top news and inject into view
         */
        //List<Share> topNews = Share.find("")

        /*
            Get most recent news and inject into view
         */
        List<Share> mostRecents = ShareDAO.getMostRecent();
        renderArgs.put("mostRecents", mostRecents);



        renderTemplate(NavigationConstants.homePage, arguments);
    }
}
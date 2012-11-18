package controllers.main;

import config.LuppeItConstants;
import config.NavigationConstants;
import models.user.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationController extends Controller {

    @Before
    static void before() {
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

        if (Cache.get("currentUser") != null) {
            homepage();
        }

        HashMap<String, String> arguments = new HashMap<String, String>();
        renderTemplate(NavigationConstants.mainPage, arguments);
    }

    static void homepage() {
        HashMap<String, String> arguments = new HashMap<String, String>();





        renderTemplate(NavigationConstants.homePage, arguments);
    }
}
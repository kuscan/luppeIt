package controllers.login;

import config.LuppeItConstants;
import config.NavigationConstants;
import controllers.BaseController;
import database.dao.user.UserDAO;
import database.dao.userpast.UserPastDAO;
import models.user.User;
import models.userpast.UserPast;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/18/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends BaseController {

    @Before
    static void before() {
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    public static void login() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        renderTemplate(NavigationConstants.loginPage, arguments);
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
            renderTemplate(NavigationConstants.loginPage, arguments);
        }


        User user = UserDAO.getUserByEmailAndPassword(email, password);
        if (user == null) {
            arguments.put("loginError", LuppeItConstants.WRONG_USERNAME_PASSWORD_ERROR_MESSAGE);
            hasError = true;
        } else if (user.getUserStatusId() == LuppeItConstants.USER_STATUS_ID_REGISTERED) {
            arguments.put("loginError", LuppeItConstants.EMAIL_NOT_CONFIRMED_ERROR_MESSAGE);
            hasError = true;
        }
        if (hasError) {
            renderTemplate(NavigationConstants.loginPage, arguments);
        }


        if (user.getUserStatusId() == LuppeItConstants.USER_STATUS_ID_ACTIVE) {
            session.put("userId", user.getUserId());
            Cache.set("user" + user.getUserId().toString(), user, "60mn");
            
            UserPast userPast = new UserPast();
            userPast.setAddedTags(UserPastDAO.getAddedTagsPast(user.getUserId()));
            userPast.setLuppedTags(UserPastDAO.getLuppedSharesTagsPast(user.getUserId()));
            userPast.setViewedTags(UserPastDAO.getViewedSharesTagsPast(user.getUserId()));
            userPast.setDiggedTags(UserPastDAO.getDiggedSharesTagsPast(user.getUserId()));
            userPast.setLuppedResources(UserPastDAO.getLuppedSharesResourcesPast(user.getUserId()));
            userPast.setViewedResources(UserPastDAO.getViewedSharesResourcesPast(user.getUserId()));
            userPast.setDiggedResources(UserPastDAO.getDiggedSharesResourcesPast(user.getUserId()));
            Cache.set("userPast" + user.getUserId().toString(), userPast, "60mn");
            
            redirect(LuppeItConstants.BASE_URL);
        }

    }

    public static void logout() {
        Cache.delete("user" + session.get("userId"));
        Cache.delete("userPast" + session.get("userId"));
        session.clear();

        redirect(LuppeItConstants.BASE_URL);
    }

}

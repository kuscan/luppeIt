package controllers.login;

import config.LuppeItConstants;
import config.NavigationConstants;
import models.user.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Scope;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/18/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends Controller {

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

        User user = User.find("email = ? and password = ?", email, password).first();
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
            Scope.Session.current().put("userId", user.getUserId());
            Cache.set("currentUser", user, "60mn");
            redirect(LuppeItConstants.BASE_URL);
        }

    }

    public static void logout() {
        Cache.delete("currentUser");
        Scope.Session.current().put("userId", null);

        redirect(LuppeItConstants.BASE_URL);
    }

}

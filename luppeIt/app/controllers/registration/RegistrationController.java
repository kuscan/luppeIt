package controllers.registration;

import config.LuppeItConstants;
import config.NavigationConstants;
import models.user.User;
import models.user.UserConfirmation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.mail.EmailException;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import utils.MailSender;
import utils.ValidationUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationController extends Controller {

    @Before
    static void before() {
        Logger.info(LuppeItConstants.BASE_URL);
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    public static void register() {
        HashMap<String, String> arguments = new HashMap<String, String>();
        arguments.put("emailValue", "");

        renderTemplate(NavigationConstants.registrationPage, arguments);
    }

    public static void completeRegistration(String email,
                                            String password,
                                            String passwordConfirmation) {

        boolean hasError = false;

        HashMap<String, String> arguments = new HashMap<String, String>();
        arguments.put("emailValue", email);

        validation.required(email);
        validation.required(password);
        validation.required(passwordConfirmation);

        if (validation.errorsMap().containsKey("email")) {
            arguments.put("emailRequiredError", LuppeItConstants.EMAIL_REQUIRED_MESSAGE);
            hasError = true;
        }
        if (validation.errorsMap().containsKey("password")) {
            arguments.put("passwordRequiredError", LuppeItConstants.PASSWORD_REQUIRED_MESSAGE);
            hasError = true;
        }
        if (validation.errorsMap().containsKey("passwordConfirmation")) {
            arguments.put("passwordConfirmationRequiredError", LuppeItConstants.PASSWORD_CONFIRMATION_REQUIRED_MESSAGE);
            hasError = true;
        }
        if (!ValidationUtils.validateEmail(email)) {
            arguments.put("emailFormatError", LuppeItConstants.EMAIL_VALIDATION_ERROR_MESSAGE);
            hasError = true;
        }
        if (!ValidationUtils.validatePassword(password)) {
            arguments.put("passwordFormatError", LuppeItConstants.PASSWORD_VALIDATION_ERROR_MESSAGE);
            hasError = true;
        }
        if (!password.equals(passwordConfirmation)) {
            arguments.put("passwordConfirmationMatchError", LuppeItConstants.PASSWORD_CONFIRMATION_MATCH_ERROR_MESSAGE);
            hasError = true;
        }
        if (hasError) {
            renderTemplate(NavigationConstants.registrationPage, arguments);
        }

        if (User.find("email = ?", email).fetch().size() != 0) {
            arguments.put("emailAlreadyExistsError", LuppeItConstants.EMAIL_ALREADY_EXISTS_ERROR_MESSAGE);
            renderTemplate(NavigationConstants.registrationPage, arguments);
        }

        String confirmationCode = RandomStringUtils.random(20, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

        try {
            MailSender.sendEmail("LuppeIt | E-mail Verification",
                                 "<html><body><h1>Welcome to LuppeIt!</h1>Please click on the following link to verify your e-mail address and start lupping!" +
                                         "<br/><a href='" + LuppeItConstants.BASE_URL + "/confirmation?code=" + confirmationCode + "'>Confirm your e-mail!</a><br/>" +
                                         "<br/><i>LuppeIt 2012</i></body></html>",
                                 LuppeItConstants.BASE_URL + "/confirmation?code=" + confirmationCode,
                                 email);
        } catch (EmailException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        UserConfirmation userConfirmation = new UserConfirmation();
        userConfirmation.setEmail(email);
        userConfirmation.setConfirmationCode(confirmationCode);
        userConfirmation.save();


        User user = new User();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.setAge(0);
        user.setTrust(0);
        user.setUserStatusId(LuppeItConstants.USER_STATUS_ID_REGISTERED);
        user.setCountryId(LuppeItConstants.COUNTRY_ID_DEFAULT);
        user.setUserTypeId(LuppeItConstants.USER_TYPE_ID_DEFAULT);
        user.save();

        renderTemplate(NavigationConstants.registrationCompletePage, arguments);

    }

    public static void validateEmail(String code) {

        Logger.info(code);

        HashMap<String, String> arguments = new HashMap<String, String>();

        List<UserConfirmation> userConfirmations = new ArrayList<UserConfirmation>();
        userConfirmations = UserConfirmation.find("confirmationCode = ?", code).fetch();

        if (userConfirmations.size() == 0) {
            arguments.put("confirmationError", LuppeItConstants.CONFIRMATION_CODE_NOT_FOUND_ERROR);
        } else {
            User user = User.find("email = ?", userConfirmations.get(0).getEmail()).first();
            user.setUserStatusId(LuppeItConstants.USER_STATUS_ID_ACTIVE);
            user.save();
        }

        renderTemplate(NavigationConstants.registrationConfirmationPage, arguments);



    }

}

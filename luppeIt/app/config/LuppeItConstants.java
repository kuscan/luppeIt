package config;

import java.util.ArrayList;
import java.util.List;

import models.status.ResourceStatus;

import play.i18n.Messages;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LuppeItConstants {
	
	/*******************************************************
	 actions start
	 *******************************************************/
	
	public static final Integer ACTION_ID_VIEW_SHARE = 1;
	public static final Integer VIEW_SHARE_EP_USER_ID_PARAM = 1;
	public static final Integer VIEW_SHARE_EP_SHARE_ID_PARAM = 2;
	
	public static final Integer ACTION_ID_LUPPE_SHARE = 2;
	public static final Integer LUPPE_SHARE_EP_USER_ID_PARAM = 3;
	public static final Integer LUPPE_SHARE_EP_SHARE_ID_PARAM = 4;
	
	public static final Integer ACTION_ID_DIG_SHARE = 3;
	public static final Integer DIG_SHARE_EP_USER_ID_PARAM = 5;
	public static final Integer DIG_SHARE_EP_SHARE_ID_PARAM = 6;
	
	public static final Integer ACTION_ID_TAG_SHARE = 4;
	public static final Integer TAG_SHARE_EP_USER_ID_PARAM = 7;
	public static final Integer TAG_SHARE_EP_TAG_ID_PARAM = 8;
	
	/*******************************************************
	 actions end
	 *******************************************************/
	
	//default user
	public static final Integer DEFAULT_USER_ID = 1;

    //basic constants
    public static final String BASE_URL = Messages.get("baseUrl");
    public static final String MAIN_PAGE_TITLE = "LuppeIt";

    //share statuses
    public static final Integer SHARE_STATUS_ACTIVE = 1;
    public static final Integer SHARE_STATUS_PASSIVE = 2;
    
    //category statuses
    public static final Integer CATEGORY_STATUS_ACTIVE = 1;
    public static final Integer CATEGORY_STATUS_PASSIVE = 2;

    //resource statuses
    public static final Integer RESOURCE_STATUS_ACTIVE = 1;
    public static final Integer RESOURCE_STATUS_PASSIVE = 2;

    //rss resource statuses
    public static final Integer RSS_RESOURCE_STATUS_ACTIVE = 1;
    public static final Integer RSS_RESOURCE_STATUS_PASSIVE = 2;

    //user statuses
    public static final Integer USER_STATUS_ID_REGISTERED = 1;
    public static final Integer USER_STATUS_ID_ACTIVE = 2;
    
    //tag statuses
    public static final Integer TAG_STATUS_ID_ACTIVE = 1;
    public static final Integer TAG_STATUS_ID_PASSIVE = 2;

    //user types
    public static final Integer RSS_READER_USER_ID = 0;
    public static final Integer USER_TYPE_ID_DEFAULT = 1;
    public static final Integer USER_TYPE_ID_ADMIN = 2;

    //default country
    public static final Integer COUNTRY_ID_DEFAULT = 1;



    //authentication error message
    public static final String USER_NOT_AUTHENTICATED_ERROR_MESSAGE = Messages.get("error.userNotAuthenticatedErrorMessage");
    
    //registration page error messages
    public static final String EMAIL_REQUIRED_MESSAGE = Messages.get("registration.emailRequiredMessage");
    public static final String EMAIL_VALIDATION_ERROR_MESSAGE = Messages.get("registration.emailValidationErrorMessage");
    public static final String PASSWORD_REQUIRED_MESSAGE = Messages.get("registration.passwordRequiredMessage");
    public static final String PASSWORD_VALIDATION_ERROR_MESSAGE = Messages.get("registration.passwordValidationErrorMessage");
    public static final String PASSWORD_CONFIRMATION_REQUIRED_MESSAGE = Messages.get("registration.passwordConfirmationRequiredMessage");
    public static final String PASSWORD_CONFIRMATION_MATCH_ERROR_MESSAGE = Messages.get("registration.passwordConfirmationMatchErrorMessage");
    public static final String EMAIL_ALREADY_EXISTS_ERROR_MESSAGE = Messages.get("registration.emailAlreadyExistsErrorMessage");
    public static final String CONFIRMATION_CODE_NOT_FOUND_ERROR = Messages.get("regsitration.confirmationCodeNotFoundErrorMessage");
    public static final String USER_CATEGORY_REQUIRED_ERROR_MESSAGE = Messages.get("registration.userCategoryRequiredErrorMessage");

    //login page error messages
    public static final String WRONG_USERNAME_PASSWORD_ERROR_MESSAGE = Messages.get("login.wrongUsernamePasswordErrorMessage");
    public static final String EMAIL_NOT_CONFIRMED_ERROR_MESSAGE = Messages.get("login.emailNotConfirmedErrorMessage");

    //submit link page error messages
    public static final String SUBMIT_LINK_URL_FORMAT_ERROR_MESSAGE = Messages.get("submitShare.urlFormatErrorMessage");
    public static final String SUBMIT_LINK_CATEGORY_ERROR_MESSAGE = Messages.get("submitShare.categoryErrorMessage");
    public static final String SUBMIT_LINK_TITLE_ERROR_MESSAGE = Messages.get("submitShare.titleErrorMessage");
    public static final String SUBMIT_LINK_DESCRIPTION_ERROR_MESSAGE = Messages.get("submitShare.descriptionErrorMessage");
    public static final String SUBMIT_LINK_RESOURCE_NOT_ACTIVE_ERROR_MESSAGE = Messages.get("submitShare.resourceNotActiveErrorMessage");
}

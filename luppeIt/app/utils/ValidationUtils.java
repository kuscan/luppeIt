package utils;

import config.LuppeItConstants;
import play.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/18/12
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationUtils {

    public static boolean validateEmail(String email) {
        try {
            InternetAddress address = new InternetAddress(email, true);
            return true;
        }
        catch (AddressException e) {
            return false;
        }
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.equals("")) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        return true;
    }

}

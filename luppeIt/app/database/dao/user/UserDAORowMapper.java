package database.dao.user;

import models.user.User;
import models.user.UserConfirmation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAORowMapper {

    /*
        Count column
     */
    public static final String COUNT_COLUMN = "COUNT(*)";

    /*
        user table columns
     */
    public static final String USER_ID_COLUMN = "user_id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String EMAIL_COLUMN = "email";
    public static final String AGE_COLUMN = "age";
    public static final String TRUST_COLUMN = "trust";
    public static final String USER_STATUS_ID_COLUMN = "user_status_id";
    public static final String COUNTRY_ID_COLUMN = "country_id";
    public static final String USER_TYPE_ID_COLUMN = "user_type_id";

    /*
        user_confirmation table columns
     */
    public static final String CONFIRMATION_CODE_COLUMN = "confirmation_code";


    public static List<User> mapUserList(ResultSet rs) {

        try {
            List<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(USER_ID_COLUMN));
                user.setUsername(rs.getString(USERNAME_COLUMN));
                user.setPassword(rs.getString(PASSWORD_COLUMN));
                user.setEmail(rs.getString(EMAIL_COLUMN));
                user.setAge(rs.getInt(AGE_COLUMN));
                user.setTrust(rs.getInt(TRUST_COLUMN));
                user.setUserStatusId(rs.getInt(USER_STATUS_ID_COLUMN));
                user.setCountryId(rs.getInt(COUNTRY_ID_COLUMN));
                user.setUserTypeId(rs.getInt(USER_TYPE_ID_COLUMN));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer mapCount(ResultSet rs) {
        try {
            Integer count = null;
            while (rs.next()) {
                count = rs.getInt(COUNT_COLUMN);
                break;
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<UserConfirmation> mapUserConfirmationList(ResultSet rs) {
        try {
            List<UserConfirmation> userConfirmations = new ArrayList<UserConfirmation>();
            while (rs.next()) {
                UserConfirmation userConfirmation = new UserConfirmation();
                userConfirmation.setEmail(rs.getString(EMAIL_COLUMN));
                userConfirmation.setConfirmationCode(rs.getString(CONFIRMATION_CODE_COLUMN));
                userConfirmations.add(userConfirmation);
            }
            return userConfirmations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

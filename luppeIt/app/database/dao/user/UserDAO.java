package database.dao.user;

import models.user.User;
import models.user.UserConfirmation;
import play.db.DB;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {

    public static final String QUERY_GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id,username,password,email,age,trust,user_status_id,country_id,user_type_id FROM user WHERE email = '{email}' AND password = '{password}'";
    public static final String QUERY_GET_USER_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM user WHERE email = '{email}'";
    public static final String QUERY_GET_USER_CONFIRMATIONS_BY_CONFIRMATION_CODE = "SELECT email, confirmation_code FROM user_confirmation WHERE confirmation_code = '{confirmation_code}'";

    public static final String QUERY_ADD_USER_CONFIRMATION = "INSERT INTO user_confirmation (email, confirmation_code) VALUES ('{email}','{confirmation_code}')";
    public static final String QUERY_ADD_USER = "INSERT INTO user (username, password, email, age, trust, user_status_id, country_id, user_type_id) VALUES ('{username}','{password}','{email}','{age}','{trust}','{user_status_id}','{country_id}','{user_type_id}')";

    public static final String QUERY_UPDATE_USER_STATUS_BY_EMAIL = "UPDATE user SET user_status_id = {user_status_id} WHERE email = '{email}'";

    public static User getUserByEmailAndPassword(String email, String password) {
        String query = QUERY_GET_USER_BY_EMAIL_AND_PASSWORD
                       .replace("{email}", email)
                       .replace("{password}", password);
        List<User> users = UserDAORowMapper.mapUserList(DB.executeQuery(query));

        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    public static Integer getUserCountByEmail(String email) {
        String query = QUERY_GET_USER_COUNT_BY_EMAIL
                       .replace("{email}", email);
        return UserDAORowMapper.mapCount(DB.executeQuery(query));
    }

    public static boolean addUserConfirmation(UserConfirmation userConfirmation) {
        String query = QUERY_ADD_USER_CONFIRMATION
                       .replace("{email}", userConfirmation.getEmail())
                       .replace("{confirmation_code}", userConfirmation.getConfirmationCode());
        return DB.execute(query);
    }

    public static boolean addUser(User user) {
        String query = QUERY_ADD_USER
                       .replace("{username}", user.getUsername())
                       .replace("{password}", user.getPassword())
                       .replace("{email}", user.getEmail())
                       .replace("{age}", user.getAge().toString())
                       .replace("{trust}", user.getTrust().toString())
                       .replace("{user_status_id}", user.getUserStatusId().toString())
                       .replace("{country_id}", user.getCountryId().toString())
                       .replace("{user_type_id}", user.getUserTypeId().toString());
        return DB.execute(query);
    }

    public static List<UserConfirmation> getUserConfirmationsByConfirmationCode(String confirmationCode) {
        String query = QUERY_GET_USER_CONFIRMATIONS_BY_CONFIRMATION_CODE
                       .replace("{confirmation_code}", confirmationCode);
        return UserDAORowMapper.mapUserConfirmationList(DB.executeQuery(query));
    }

    public static boolean updateUserStatusByEmail(String email, Integer userStatusId) {
        String query = QUERY_UPDATE_USER_STATUS_BY_EMAIL
                       .replace("{user_status_id}", userStatusId.toString())
                       .replace("{email}", email);
        return DB.execute(query);
    }
}

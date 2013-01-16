package database.dao.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import exception.ProvisionException;

import play.Logger;
import play.db.DB;
import play.db.jpa.Transactional;

public class ActionDAO {

	public static final String QUERY_ADD_USER_ACTION = "INSERT INTO user_action (action_id,user_id) VALUES (?,?)";
	public static final String QUERY_ADD_USER_ACTION_PARAMETER_VALUE = "INSERT INTO user_action_parameter_value (user_action_id,action_parameter_id,parameter_value) VALUES (?,?,?)";
	public static final String QUERY_CHECK_USER_ACTION = "SELECT COUNT(ua.user_action_id) FROM user_action ua INNER JOIN user_action_parameter_value uapv ON ua.user_action_id = uapv.user_action_id WHERE ua.user_id = ? AND ua.action_id = ? AND uapv.action_parameter_id = ? AND uapv.parameter_value = ?"; 
	public static final String QUERY_GET_USER_ACTION_ID_WITH_PARAMETERS = "SELECT ua.user_action_id FROM user_action ua " +
																		  "INNER JOIN user_action_parameter_value uapv " +
																		  "ON ua.user_action_id = uapv.user_action_id " +
																		  "WHERE ua.user_id = ? AND ua.action_id = ? AND uapv.action_parameter_id = ? AND uapv.parameter_value = ?";
	public static final String QUERY_DELETE_USER_ACTION_PARAMETERS = "DELETE FROM user_action_parameter_value WHERE user_action_id = ?";
	public static final String QUERY_DELETE_USER_ACTION = "DELETE FROM user_action WHERE user_action_id = ?;";
	
	public static Long addUserAction(Integer actionId, Integer userId) throws ProvisionException {
		try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_USER_ACTION, Statement.RETURN_GENERATED_KEYS);
    		ps.setInt(1, actionId);
    		ps.setInt(2, userId);
    		ps.executeUpdate();
    		ResultSet rs = ps.getGeneratedKeys();
    		if (rs.next()) {
    			return rs.getLong(1);
    		}
    		Logger.error("ActionDAO.addUserAction cannot add user action");
    		throw new ProvisionException("00000", "ActionDAO.addUserAction cannot add user action");
    	} catch (SQLException e) {
    		Logger.error(e, "ActionDAO.addUserAction caught SQLException");
    		throw new ProvisionException("00000", "ActionDAO.addUserAction caught SQLException");
    	}
	}
	
	public static Boolean addUserActionParameterValue(Long userActionId, Integer actionParameterId, String parameterValue) throws ProvisionException {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_USER_ACTION_PARAMETER_VALUE);
			ps.setLong(1, userActionId);
			ps.setInt(2, actionParameterId);
			ps.setString(3, parameterValue);
			return ps.execute();
		} catch (SQLException e) {
			Logger.error(e, "ActionDAO.addUserActionParameterValue caught SQLException");
			throw new ProvisionException("00000", "ActionDAO.addUserActionParameterValue caught SQLException");
		}
	}
	
	public static Integer checkIfUserActionExistsForShare(Integer userId, Integer actionId, Integer actionParameterId, String parameterValue) throws ProvisionException {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_CHECK_USER_ACTION);
			ps.setInt(1, userId);
			ps.setInt(2, actionId);
			ps.setInt(3, actionParameterId);
			ps.setString(4, parameterValue);
			return ActionDAORowMapper.mapUserActionCount(ps.executeQuery());
		} catch (SQLException e) {
			Logger.error(e, "ActionDAO.checkIfUserActionExistsForShare caught SQLException");
			throw new ProvisionException("00000", "ActionDAO.checkIfUserActionExistsForShare caught SQLException");
		}
	}
	
	@Transactional
	public static boolean deleteUserActionAndParameters(Integer userId, Integer actionId, Integer actionParameterId, String parameterValue) throws ProvisionException {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_USER_ACTION_ID_WITH_PARAMETERS);
			ps.setInt(1, userId);
			ps.setInt(2, actionId);
			ps.setInt(3, actionParameterId);
			ps.setString(4, parameterValue);
			Long userActionId = ActionDAORowMapper.mapUserActionId(ps.executeQuery());
			
			ps = DB.getConnection().prepareStatement(QUERY_DELETE_USER_ACTION_PARAMETERS);
			ps.setLong(1, userActionId);
			ps.execute();
			
			ps = DB.getConnection().prepareStatement(QUERY_DELETE_USER_ACTION);
			ps.setLong(1, userActionId);
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			Logger.error(e, "ActionDAO.deleteUserActionAndParameters caught SQLException");
			throw new ProvisionException("00000", "ActionDAO.deleteUserActionAndParameters caught SQLException");
		}
	}
	
}

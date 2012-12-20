package database.dao.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import exception.ProvisionException;

import play.Logger;
import play.db.DB;

public class ActionDAO {

	public static final String QUERY_ADD_USER_ACTION = "INSERT INTO user_action (action_id,user_id) VALUES (?,?)";
	public static final String QUERY_ADD_USER_ACTION_PARAMETER_VALUE = "INSERT INTO user_action_parameter_value (user_action_id,action_parameter_id,parameter_value) VALUES (?,?,?)";
	
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
	
}

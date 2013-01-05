package database.dao.action;

import models.share.Category;
import models.status.CategoryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActionDAORowMapper {

	public static Integer mapUserActionCount(ResultSet rs) {
		try {
			rs.next();
			return rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Long mapUserActionId(ResultSet rs) {
		try {
			rs.next();
			return rs.getLong(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

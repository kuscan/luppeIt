package database.dao.userpast;

import models.share.Share;
import models.userpast.UserPastUnit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserPastDAORowMapper {

    public static final String COLUMN_UAPV_ACTION_PARAMETER_ID = "uapv.action_parameter_id";
    public static final String COLUMN_UAPV_PARAMETER_VALUE = "uapv.parameter_value";
    
    public static final String COLUMN_SHARE_TAG_TAG_ID = "st.tag_id";
    
    public static final String COLUMN_RSS_RESOURCE_PARENT_RESOURCE_ID = "rr.parent_resourc_id";
    
    public static List<Integer> mapGetAddedTagsPast(ResultSet rs) {
    	try {
    		List<Integer> userPastList = new ArrayList<Integer>();
    		while (rs.next()) {
    			userPastList.add(rs.getInt(COLUMN_UAPV_PARAMETER_VALUE));
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<Integer> mapGetLuppedSharesTagsPast(ResultSet rs) {
    	try {
    		List<Integer> userPastList = new ArrayList<Integer>();
    		while (rs.next()) {
    			userPastList.add(rs.getInt(COLUMN_SHARE_TAG_TAG_ID));
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<Integer> mapGetLuppedSharesResourcesPast(ResultSet rs) {
    	try {
    		List<Integer> userPastList = new ArrayList<Integer>();
    		while (rs.next()) {
    			userPastList.add(rs.getInt(COLUMN_RSS_RESOURCE_PARENT_RESOURCE_ID));
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
}

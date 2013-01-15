package database.dao.userpast;

import models.share.Share;
import models.userpast.UserPastUnit;
import play.Logger;
import play.db.DB;
import play.db.jpa.JPQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import config.LuppeItConstants;



/**
 * Created with IntelliJ IDEA.
 * User: farukkuscan
 * Date: 11/21/12
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserPastDAO {

    /*
        Query strings for ShareDAO
     */
    public static final String QUERY_GET_ADDED_TAGS_PAST = "SELECT uapv.action_parameter_id, uapv.parameter_value " +  
												    		"FROM user_action AS ua " +
												    		"INNER JOIN user_action_parameter_value AS uapv ON ua.user_action_id = uapv.user_action_id " + 
												    		"WHERE " +
												    		"ua.user_id = ? AND " +
												    		"ua.action_id = ? AND " +
												    		"uapv.action_parameter_id = ?";
    public static final String QUERY_GET_LUPPED_SHARES_TAGS_PAST = "SELECT st.tag_id " +
															"FROM user_action AS ua " +
															"INNER JOIN user_action_parameter_value AS uapv ON ua.user_action_id = uapv.user_action_id " + 
															"INNER JOIN share_tag AS st ON uapv.parameter_value = st.share_id " +
															"WHERE " +
															"ua.user_id = ? AND " + 
															"ua.action_id = ? AND " +
															"uapv.action_parameter_id = ?";
    public static final String QUERY_GET_LUPPED_SHARES_RESOURCES_PAST = "SELECT rr.parent_resource_id " +
															"FROM user_action AS ua " +
															"INNER JOIN user_action_parameter_value AS uapv ON ua.user_action_id = uapv.user_action_id " + 
															"INNER JOIN share AS s ON uapv.parameter_value = s.share_id " +
															"INNER JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
															"WHERE " +
															"ua.user_id = ? AND " + 
															"ua.action_id = ? AND " +
															"uapv.action_parameter_id = ?";
    
    public static List<UserPastUnit> getAddedTagsPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ADDED_TAGS_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_TAG_SHARE);
    		ps.setInt(3, LuppeItConstants.TAG_SHARE_EP_TAG_ID_PARAM);
    		List<Integer> tagIds = UserPastDAORowMapper.mapGetAddedTagsPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer tagId: tagIds) {
    			int index = returnFoundIndex(userPastList, tagId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(tagId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static int returnFoundIndex(List<UserPastUnit> userPastList, Integer id) {
    	for (int i = 0; i < userPastList.size(); i++) {
    		if (userPastList.get(i).getId().intValue() == id.intValue()) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static List<UserPastUnit> getLuppedSharesTagsPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_TAGS_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_LUPPE_SHARE);
    		ps.setInt(3, LuppeItConstants.LUPPE_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> tagIds = UserPastDAORowMapper.mapGetLuppedSharesTagsPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer tagId: tagIds) {
    			int index = returnFoundIndex(userPastList, tagId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(tagId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<UserPastUnit> getViewedSharesTagsPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_TAGS_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_VIEW_SHARE);
    		ps.setInt(3, LuppeItConstants.VIEW_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> tagIds = UserPastDAORowMapper.mapGetLuppedSharesTagsPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer tagId: tagIds) {
    			int index = returnFoundIndex(userPastList, tagId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(tagId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<UserPastUnit> getDiggedSharesTagsPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_TAGS_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_DIG_SHARE);
    		ps.setInt(3, LuppeItConstants.DIG_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> tagIds = UserPastDAORowMapper.mapGetLuppedSharesTagsPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer tagId: tagIds) {
    			int index = returnFoundIndex(userPastList, tagId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(tagId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<UserPastUnit> getLuppedSharesResourcesPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_RESOURCES_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_LUPPE_SHARE);
    		ps.setInt(3, LuppeItConstants.LUPPE_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> resourceIds = UserPastDAORowMapper.mapGetLuppedSharesResourcesPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer resourceId: resourceIds) {
    			int index = returnFoundIndex(userPastList, resourceId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(resourceId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<UserPastUnit> getViewedSharesResourcesPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_RESOURCES_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_VIEW_SHARE);
    		ps.setInt(3, LuppeItConstants.VIEW_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> resourceIds = UserPastDAORowMapper.mapGetLuppedSharesResourcesPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer resourceId: resourceIds) {
    			int index = returnFoundIndex(userPastList, resourceId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(resourceId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<UserPastUnit> getDiggedSharesResourcesPast(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_LUPPED_SHARES_RESOURCES_PAST);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.ACTION_ID_DIG_SHARE);
    		ps.setInt(3, LuppeItConstants.DIG_SHARE_EP_SHARE_ID_PARAM);
    		List<Integer> resourceIds = UserPastDAORowMapper.mapGetLuppedSharesResourcesPast(ps.executeQuery());
    		
    		List<UserPastUnit> userPastList = new ArrayList<UserPastUnit>();
    		for (Integer resourceId: resourceIds) {
    			int index = returnFoundIndex(userPastList, resourceId);
    			if (index > -1) {
    				userPastList.get(index).setAmount(userPastList.get(index).getAmount() + 1);
    			} else {
    				UserPastUnit unit = new UserPastUnit();
    				unit.setId(resourceId);
    				unit.setAmount(1);
    				userPastList.add(unit);
    			}
    		}
    		return userPastList;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}

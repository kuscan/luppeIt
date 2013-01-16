package database.dao.share;

import models.share.Share;
import models.userpast.UserPastUnit;
import play.Logger;
import play.db.DB;
import play.db.jpa.JPQL;
import play.db.jpa.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import config.LuppeItConstants;
import database.dao.tag.TagDAO;



/**
 * Created with IntelliJ IDEA.
 * User: farukkuscan
 * Date: 11/21/12
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShareDAO {

    /*
        Constant values
     */
    public static final Integer MOST_RECENT_LIMIT = 30;
    public static final Integer TOP_NEWS_LIMIT = 30;
    public static final Integer MOST_RECENT_LIMIT_FOR_REGISTERED_USER = 200;
    public static final Integer TOP_NEWS_LIMIT_FOR_REGISTERED_USER = 200;

    /*
        Query strings for ShareDAO
     */
    public static final String QUERY_GET_MOST_RECENT = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.author, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.rss_resource_id, s.user_id, s.last_modified_date, r.resource_name, c.category_name " +
                                                       "FROM share AS s " +
                                                       "JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
                                                       "JOIN resource as r ON rr.parent_resource_id = r.resource_id " +
                                                       "JOIN category as c ON s.category_id = c.category_id " +
                                                       "WHERE " +
                                                       "s.share_status_id = 1 AND " +
                                                       "rr.rss_resource_status_id = 1 " +
                                                       "ORDER BY s.last_modified_date DESC LIMIT ?";
    public static final String QUERY_GET_TOP_NEWS = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.author, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.rss_resource_id, s.user_id, s.last_modified_date, r.resource_name, c.category_name " +
											            "FROM share AS s " +
											            "JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
											            "JOIN resource as r ON rr.parent_resource_id = r.resource_id " +
											            "JOIN category as c ON s.category_id = c.category_id " +
											            "WHERE " +
											            "s.last_modified_date > ? AND " +
											            "s.share_status_id = 1 AND " +
											            "rr.rss_resource_status_id = 1 " +
											            "ORDER BY s.luppe_count DESC, s.view_count DESC LIMIT ?";
    public static final String QUERY_GET_SHARES_BY_RSS_RESOURCE_ID = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.author, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.rss_resource_id, s.user_id, s.last_modified_date " +
                                                                     "FROM share AS s WHERE " +
                                                                     "s.rss_resource_id = {RSS_RESOURCE_ID}";
    public static final String QUERY_ADD_SHARE = "INSERT INTO share (title,description,content,url,author,luppe_count,dig_count,view_count,category_id,share_status_id,rss_resource_id,user_id,last_modified_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String QUERY_GET_SHARE_BY_SHARE_ID = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.author, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.rss_resource_id, s.user_id, s.last_modified_date, r.resource_name, c.category_name " +
    													"FROM share AS s " +
    													"JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
    													"JOIN resource AS r ON rr.parent_resource_id = r.resource_id " +
    													"JOIN category AS c ON rr.category_id = c.category_id " +
    													"WHERE s.share_id = ?";
    public static final String QUERY_UPDATE_SHARE_VIEW_COUNT_INCREASE_BY_ONE = "UPDATE share SET view_count = view_count + 1 WHERE share_id = ?";
    public static final String QUERY_UPDATE_SHARE_LUPPE_COUNT_INCREASE_BY_ONE = "UPDATE share SET luppe_count = luppe_count + 1 WHERE share_id = ?";
    public static final String QUERY_UPDATE_SHARE_DIG_COUNT_INCREASE_BY_ONE = "UPDATE share SET dig_count = dig_count + 1 WHERE share_id = ?";
    public static final String QUERY_UPDATE_SHARE_LUPPE_COUNT_DECREASE_BY_ONE = "UPDATE share SET luppe_count = luppe_count - 1 WHERE share_id = ?";
    public static final String QUERY_UPDATE_SHARE_DIG_COUNT_DECREASE_BY_ONE = "UPDATE share SET dig_count = dig_count - 1 WHERE share_id = ?";
    public static final String QUERY_GET_SHARES_OF_LAST_WEEK_WITH_DETAILS_BY_CATEGORY_ID = "SELECT s.share_id,s.title,s.description,s.content,s.url,s.author,s.luppe_count,s.dig_count,s.view_count,s.category_id,s.share_status_id,s.rss_resource_id,s.user_id,s.last_modified_date,r.resource_name,c.category_name " + 
    																							"FROM share AS s " +
    																							"JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
    																							"JOIN resource AS r ON rr.parent_resource_id = r.resource_id " +
    																							"JOIN category AS c ON rr.category_id = c.category_id " +
    																							"WHERE " +
    																							"s.category_id = ? " +
    																							"AND s.share_status_id = ? " +
    																							"AND s.last_modified_date > ? " +
    																							"ORDER BY s.last_modified_date DESC";
    public static final String QUERY_GET_RESOURCES_OF_SHARES_1 = "SELECT s.share_id,rr.parent_resource_id FROM share AS s INNER JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id WHERE s.share_id IN (REPLACE_THIS)";
    
    public static List<Share> getMostRecent() {
        try {
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_MOST_RECENT);
        	ps.setInt(1, MOST_RECENT_LIMIT);
        	return ShareDAORowMapper.mapShareListWithDetails(ps.executeQuery());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public static List<Share> getTopNews() {
        try {
        	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DATE, -1);
        	
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_TOP_NEWS);
        	ps.setLong(1, cal.getTimeInMillis());
        	ps.setInt(2, TOP_NEWS_LIMIT);
        	return ShareDAORowMapper.mapShareListWithDetails(ps.executeQuery());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public static List<Share> getMostRecentForRegisteredUser() {
        try {
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_MOST_RECENT);
        	ps.setInt(1, MOST_RECENT_LIMIT);
        	return ShareDAORowMapper.mapShareListWithDetails(ps.executeQuery());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public static List<Share> getTopNewsForRegisteredUser() {
        try {
        	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DATE, -1);
        	
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_TOP_NEWS);
        	ps.setLong(1, cal.getTimeInMillis());
        	ps.setInt(2, TOP_NEWS_LIMIT);
        	return ShareDAORowMapper.mapShareListWithDetails(ps.executeQuery());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }

    public static List<Share> getSharesByRssResourceId(Integer rssResourceId) {
        String query = QUERY_GET_SHARES_BY_RSS_RESOURCE_ID
                       .replace("{RSS_RESOURCE_ID}", rssResourceId.toString());
        return ShareDAORowMapper.mapShareList(DB.executeQuery(query));
    }

    public static Integer addShare(Share share) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_SHARE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, share.getTitle());
			ps.setString(2, share.getDescription());
			ps.setString(3, share.getContent());
			ps.setString(4, share.getUrl());
			ps.setString(5, share.getAuthor());
			ps.setInt(6, share.getLuppeCount());
			ps.setInt(7, share.getDigCount());
			ps.setInt(8, share.getViewCount());
			ps.setInt(9, share.getCategoryId());
			ps.setInt(10, share.getShareStatusId());
			ps.setInt(11, share.getRssResourceId());
			ps.setInt(12, share.getUserId());
			ps.setLong(13, share.getLastModifiedDate());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
    		if (rs.next()) {
    			return rs.getInt(1);
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    public static Share getShareByShareId(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_SHARE_BY_SHARE_ID);
    		ps.setInt(1, shareId);
    		return ShareDAORowMapper.mapShareWithDetails(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static boolean updateShareViewCountIncreaseByOne(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_VIEW_COUNT_INCREASE_BY_ONE);
    		ps.setInt(1, shareId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateShareLuppeCountIncreaseByOne(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_LUPPE_COUNT_INCREASE_BY_ONE);
    		ps.setInt(1, shareId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateShareDigCountIncreaseByOne(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_DIG_COUNT_INCREASE_BY_ONE);
    		ps.setInt(1, shareId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateShareLuppeCountDecreaseByOne(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_LUPPE_COUNT_DECREASE_BY_ONE);
    		ps.setInt(1, shareId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateShareDigCountDecreaseByOne(Integer shareId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_DIG_COUNT_DECREASE_BY_ONE);
    		ps.setInt(1, shareId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static List<Share> getSharesOfLastWeekWithDetailsByCategoryId(Integer categoryId) {
    	try {
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, -7);
    		
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_SHARES_OF_LAST_WEEK_WITH_DETAILS_BY_CATEGORY_ID);
    		ps.setInt(1, categoryId);
    		ps.setInt(2, LuppeItConstants.SHARE_STATUS_ACTIVE);
    		ps.setTimestamp(3, new java.sql.Timestamp(cal.getTimeInMillis()));
    		return ShareDAORowMapper.mapShareListWithDetails(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    @Transactional
    public static void addTagToShare(String tag, Integer shareId) {
    	try {
    		tag = tag.toLowerCase();
    		Long tagId = TagDAO.addTag(tag);
    		if (tagId == null) {
    			tagId = TagDAO.getTagIdOfTag(tag);
    		}
    		TagDAO.addTagToShare(tagId, shareId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static Map<Integer, Integer> getResourcesOfShares(List<Share> shares) {
    	try {
    		String replaceThis = null;
    		for (Share share: shares) {
    			if (replaceThis != null) {
    				replaceThis += ",'" + share.getShareId().toString() + "'";
    			} else {
    				replaceThis = "'" + share.getShareId().toString() + "'";
    			}
    		}
    		
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_RESOURCES_OF_SHARES_1.replace("REPLACE_THIS", replaceThis));
    		return ShareDAORowMapper.mapResourcesOfShares(ps.executeQuery());
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
}

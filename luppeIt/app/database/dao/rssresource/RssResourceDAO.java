package database.dao.rssresource;

import config.LuppeItConstants;
import models.share.RssResource;
import models.status.RssResourceStatus;
import play.db.DB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssResourceDAO {

    public static final String QUERY_GET_RSS_RESOURCES_TO_PARSE = "SELECT rss_resource_id, rss_resource_name, url, parent_resource_id, rss_resource_status_id, category_id, next_feed_date, update_interval_minute " +
                                                                  "FROM rss_resource " +
                                                                  "WHERE next_feed_date < ? AND rss_resource_status_id = 1 " +
                                                                  "ORDER BY next_feed_date ASC";
    public static final String QUERY_UPDATE_RSS_RESOURCE_NEXT_FEED_DATE = "UPDATE rss_resource SET next_feed_date = ? WHERE rss_resource_id = ?";
    public static final String QUERY_GET_ALL_RSS_RESOURCES = "SELECT rss_resource_id, rss_resource_name, url, parent_resource_id, rss_resource_status_id, category_id, next_feed_date, update_interval_minute FROM rss_resource ORDER BY rss_resource_name";
    public static final String QUERY_GET_ALL_RSS_RESOURCE_STATUSES = "SELECT rss_resource_status_id, rss_resource_status_name FROM rss_resource_status ORDER BY rss_resource_status_id";
    public static final String QUERY_ADD_RSS_RESOURCE = "INSERT INTO rss_resource (rss_resource_name, url, parent_resource_id, rss_resource_status_id, category_id, update_interval_minute, next_feed_date) VALUES (?,?,?,?,?,?,?)"; 
    public static final String QUERY_UPDATE_RSS_RESOURCE = "UPDATE rss_resource SET rss_resource_name = ?, url = ?, parent_resource_id = ?, rss_resource_status_id = ?, category_id = ?, update_interval_minute = ?, next_feed_date = ? WHERE rss_resource_id = ?";
    public static final String QUERY_DELETE_RSS_RESOURCE = "DELETE FROM rss_resource WHERE rss_resource_id = ?";
    public static final String QUERY_GET_RSS_RESOURCE_BY_RSS_RESOURCE_ID = "SELECT rss_resource_id, rss_resource_name, url, parent_resource_id, rss_resource_status_id, category_id, update_interval_minute, next_feed_date FROM rss_resource WHERE rss_resource_id = ?";
    
    public static List<RssResource> getRssResourcesToParse() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_RSS_RESOURCES_TO_PARSE);
    		ps.setLong(1, Calendar.getInstance().getTimeInMillis());
    		return RssResourceDAORowMapper.mapRssResourceList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static boolean updateRssResourceNextFeedDate(Integer rssResourceId, Long nextFeedDateInMillis) {
    	try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_RSS_RESOURCE_NEXT_FEED_DATE);
			ps.setLong(1, nextFeedDateInMillis);
			ps.setInt(2, rssResourceId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public static List<RssResource> getAllRssResources() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_RSS_RESOURCES);
    		return RssResourceDAORowMapper.mapRssResourceList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<RssResourceStatus> getAllRssResourceStatuses() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_RSS_RESOURCE_STATUSES);
    		return RssResourceDAORowMapper.mapRssResourceStatusList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static boolean addRssResource(RssResource rssResource) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_RSS_RESOURCE);
    		ps.setString(1, rssResource.getRssResourceName());
    		ps.setString(2, rssResource.getUrl());
    		ps.setInt(3, rssResource.getParentResourceId());
    		ps.setInt(4, rssResource.getRssResourceStatusId());
    		ps.setInt(5, rssResource.getCategoryId());
    		ps.setInt(6, rssResource.getUpdateIntervalMinute());
    		ps.setLong(7, rssResource.getNextFeedDate());
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateRssResource(RssResource rssResource) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_RSS_RESOURCE);
    		ps.setString(1, rssResource.getRssResourceName());
    		ps.setString(2, rssResource.getUrl());
    		ps.setInt(3, rssResource.getParentResourceId());
    		ps.setInt(4, rssResource.getRssResourceStatusId());
    		ps.setInt(5, rssResource.getCategoryId());
    		ps.setInt(6, rssResource.getUpdateIntervalMinute());
    		ps.setLong(7, rssResource.getNextFeedDate());
    		ps.setInt(8, rssResource.getRssResourceId());
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean deleteRssResource(Integer rssResourceId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_DELETE_RSS_RESOURCE);
    		ps.setInt(1, rssResourceId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static RssResource getRssResourceByRssResourceId(Integer rssResourceId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_RSS_RESOURCE_BY_RSS_RESOURCE_ID);
    		ps.setInt(1, rssResourceId);
    		return RssResourceDAORowMapper.mapRssResourceList(ps.executeQuery()).get(0);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

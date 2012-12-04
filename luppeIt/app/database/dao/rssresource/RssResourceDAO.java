package database.dao.rssresource;

import config.LuppeItConstants;
import models.share.RssResource;
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
    public static final String QUERY_UPDATE_RSS_RESOURCE = "UPDATE rss_resource SET next_feed_date = ? WHERE rss_resource_id = ?";

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
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_RSS_RESOURCE);
			ps.setLong(1, nextFeedDateInMillis);
			ps.setInt(2, rssResourceId);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

}

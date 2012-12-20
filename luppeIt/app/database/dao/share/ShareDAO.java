package database.dao.share;

import models.share.Share;
import play.db.DB;
import play.db.jpa.JPQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



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

    /*
        Query strings for ShareDAO
     */
    public static final String QUERY_GET_MOST_RECENT = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.author, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.rss_resource_id, s.user_id, s.last_modified_date, r.resource_name " +
                                                       "FROM share AS s " +
                                                       "JOIN rss_resource AS rr ON s.rss_resource_id = rr.rss_resource_id " +
                                                       "JOIN resource as r ON rr.parent_resource_id = r.resource_id " +
                                                       "WHERE " +
                                                       "s.share_status_id = 1 AND " +
                                                       "rr.rss_resource_status_id = 1 " +
                                                       "ORDER BY s.last_modified_date DESC LIMIT ?";
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
    
    public static List<Share> getMostRecent() {
        try {
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_MOST_RECENT);
        	ps.setInt(1, MOST_RECENT_LIMIT);
        	return ShareDAORowMapper.mapShareListWithResourceName(ps.executeQuery());
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

    public static boolean addShare(Share share) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_SHARE);
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
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
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

}

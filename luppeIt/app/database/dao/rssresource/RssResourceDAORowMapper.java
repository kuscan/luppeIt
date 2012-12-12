package database.dao.rssresource;

import models.share.Category;
import models.share.RssResource;
import models.status.RssResourceStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 12/3/12
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class RssResourceDAORowMapper {

    public static final String RSS_RESOURCE_ID_COLUMN = "rss_resource_id";
    public static final String RSS_RESOURCE_NAME_COLUMN = "rss_resource_name";
    public static final String URL_COLUMN = "url";
    public static final String PARENT_RESOURCE_ID_COLUMN = "parent_resource_id";
    public static final String RSS_RESOURCE_STATUS_ID_COLUMN = "rss_resource_status_id";
    public static final String CATEGORY_ID_COLUMN = "category_id";
    public static final String NEXT_FEED_DATE_COLUMN = "next_feed_date";
    public static final String UPDATE_INTERVAL_MINUTE_COLUMN = "update_interval_minute";
    
    public static final String RSS_RESOURCE_STATUS_NAME_COLUMN = "rss_resource_status_name";

    public static List<RssResource> mapRssResourceList(ResultSet rs) {
        try {
            List<RssResource> rssResources = new ArrayList<RssResource>();
            while (rs.next()) {
                RssResource rssResource = new RssResource();
                rssResource.setRssResourceId(rs.getInt(RSS_RESOURCE_ID_COLUMN));
                rssResource.setRssResourceName(rs.getString(RSS_RESOURCE_NAME_COLUMN));
                rssResource.setUrl(rs.getString(URL_COLUMN));
                rssResource.setParentResourceId(rs.getInt(PARENT_RESOURCE_ID_COLUMN));
                rssResource.setRssResourceStatusId(rs.getInt(RSS_RESOURCE_STATUS_ID_COLUMN));
                rssResource.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                rssResource.setNextFeedDate(rs.getLong(NEXT_FEED_DATE_COLUMN));
                rssResource.setUpdateIntervalMinute(rs.getInt(UPDATE_INTERVAL_MINUTE_COLUMN));
                rssResources.add(rssResource);
            }
            return rssResources;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<RssResourceStatus> mapRssResourceStatusList(ResultSet rs) {
    	try {
    		List<RssResourceStatus> rssResourceStatuses = new ArrayList<RssResourceStatus>();
    		while (rs.next()) {
    			RssResourceStatus rssResourceStatus = new RssResourceStatus();
    			rssResourceStatus.setRssResourceStatusId(rs.getInt(RSS_RESOURCE_STATUS_ID_COLUMN));
    			rssResourceStatus.setRssResourceStatusName(rs.getString(RSS_RESOURCE_STATUS_NAME_COLUMN));
    			rssResourceStatuses.add(rssResourceStatus);
    		}
    		return rssResourceStatuses;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

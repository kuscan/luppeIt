package database.dao.rssresource;

import config.LuppeItConstants;
import models.share.RssResource;
import play.db.DB;

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
                                                                  "WHERE next_feed_date < NOW() AND rss_resource_status_id = 1 " +
                                                                  "ORDER BY next_feed_date ASC";

    public static List<RssResource> getRssResourcesToParse() {
        return RssResourceDAORowMapper.mapRssResourceList(DB.executeQuery(QUERY_GET_RSS_RESOURCES_TO_PARSE));
    }
    
    public static boolean updateRssResource(RssResource rssResource) {
    	return false;
    }

}

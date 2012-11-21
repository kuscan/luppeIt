package database.dao;

import models.share.Share;

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
    public static final Integer TOP_NEWS_LIMIT = 30;
    public static final Integer MOST_RECENT_LIMIT = 30;

    /*
        Query strings for ShareDAO
     */
    public static final String GET_TOP_NEWS_QUERY = "ORDER BY shareId DESC";
    public static final String GET_MOST_RECENT_QUERY = "ORDER BY shareId DESC";

    public static List<Share> getTopNews() {
        return Share.find(GET_TOP_NEWS_QUERY).fetch(TOP_NEWS_LIMIT);
    }

    public static List<Share> getMostRecent() {
        return Share.find(GET_MOST_RECENT_QUERY).fetch(MOST_RECENT_LIMIT);
    }

}

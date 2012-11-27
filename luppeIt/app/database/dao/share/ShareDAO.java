package database.dao.share;

import models.share.Share;
import play.db.DB;

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
    public static final String QUERY_GET_MOST_RECENT = "SELECT s.share_id, s.title, s.description, s.content, s.url, s.luppe_count, s.dig_count, s.view_count, s.category_id, s.share_status_id, s.resource_id, s.last_modified_date " +
                                                       "FROM share AS s JOIN resource AS r ON s.resource_id = r.resource_id " +
                                                       "WHERE " +
                                                       "s.share_status_id = 1 AND " +
                                                       "r.resource_status_id = 1 " +
                                                       "ORDER BY s.last_modified_date DESC LIMIT {MOST_RECENT_LIMIT}";

    public static List<Share> getMostRecent() {
        String query = QUERY_GET_MOST_RECENT
                       .replace("{MOST_RECENT_LIMIT}", MOST_RECENT_LIMIT.toString());
        return ShareDAORowMapper.mapShareList(DB.executeQuery(query));
    }

}

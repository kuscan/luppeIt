package database.dao.share;

import models.share.Share;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShareDAORowMapper {

    public static final String SHARE_ID_COLUMN = "s.share_id";
    public static final String TITLE_COLUMN = "s.title";
    public static final String DESCRIPTION_COLUMN = "s.description";
    public static final String CONTENT_COLUMN = "s.content";
    public static final String URL_COLUMN = "s.url";
    public static final String AUTHOR_COLUMN = "s.author";
    public static final String LUPPE_COUNT_COLUMN = "s.luppe_count";
    public static final String DIG_COUNT_COLUMN = "s.dig_count";
    public static final String VIEW_COUNT_COLUMN = "s.view_count";
    public static final String CATEGORY_ID_COLUMN = "s.category_id";
    public static final String SHARE_STATUS_ID_COLUMN = "s.share_status_id";
    public static final String RSS_RESOURCE_ID_COLUMN = "s.rss_resource_id";
    public static final String USER_ID_COLUMN = "s.user_id";
    public static final String LAST_MODIFIED_DATE_COLUMN = "s.last_modified_date";
    
    public static final String RESOURCE_NAME_COLUMN = "r.resource_name";
    public static final String CATEGORY_NAME_COLUMN = "c.category_name";

    public static List<Share> mapShareList(ResultSet rs) {
        try {
            List<Share> shares = new ArrayList<Share>();
            while (rs.next()) {
                Share share = new Share();
                share.setShareId(rs.getInt(SHARE_ID_COLUMN));
                share.setTitle(rs.getString(TITLE_COLUMN));
                share.setDescription(rs.getString(DESCRIPTION_COLUMN));
                share.setContent(rs.getString(CONTENT_COLUMN));
                share.setUrl(rs.getString(URL_COLUMN));
                share.setAuthor(rs.getString(AUTHOR_COLUMN));
                share.setLuppeCount(rs.getInt(LUPPE_COUNT_COLUMN));
                share.setDigCount(rs.getInt(DIG_COUNT_COLUMN));
                share.setViewCount(rs.getInt(VIEW_COUNT_COLUMN));
                share.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                share.setShareStatusId(rs.getInt(SHARE_STATUS_ID_COLUMN));
                share.setRssResourceId(rs.getInt(RSS_RESOURCE_ID_COLUMN));
                share.setUserId(rs.getInt(USER_ID_COLUMN));
                share.setLastModifiedDate(rs.getLong(LAST_MODIFIED_DATE_COLUMN));
                shares.add(share);
            }
            return shares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Share> mapShareListWithResourceName(ResultSet rs) {
        try {
            List<Share> shares = new ArrayList<Share>();
            while (rs.next()) {
                Share share = new Share();
                share.setShareId(rs.getInt(SHARE_ID_COLUMN));
                share.setTitle(rs.getString(TITLE_COLUMN));
                share.setDescription(rs.getString(DESCRIPTION_COLUMN));
                share.setContent(rs.getString(CONTENT_COLUMN));
                share.setUrl(rs.getString(URL_COLUMN));
                share.setAuthor(rs.getString(AUTHOR_COLUMN));
                share.setLuppeCount(rs.getInt(LUPPE_COUNT_COLUMN));
                share.setDigCount(rs.getInt(DIG_COUNT_COLUMN));
                share.setViewCount(rs.getInt(VIEW_COUNT_COLUMN));
                share.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                share.setShareStatusId(rs.getInt(SHARE_STATUS_ID_COLUMN));
                share.setRssResourceId(rs.getInt(RSS_RESOURCE_ID_COLUMN));
                share.setUserId(rs.getInt(USER_ID_COLUMN));
                share.setLastModifiedDate(rs.getLong(LAST_MODIFIED_DATE_COLUMN));
                share.setResourceName(rs.getString(RESOURCE_NAME_COLUMN));
                shares.add(share);
            }
            return shares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Share mapShareWithDetails(ResultSet rs) {
    	try {
            List<Share> shares = new ArrayList<Share>();
            while (rs.next()) {
                Share share = new Share();
                share.setShareId(rs.getInt(SHARE_ID_COLUMN));
                share.setTitle(rs.getString(TITLE_COLUMN));
                share.setDescription(rs.getString(DESCRIPTION_COLUMN));
                share.setContent(rs.getString(CONTENT_COLUMN));
                share.setUrl(rs.getString(URL_COLUMN));
                share.setAuthor(rs.getString(AUTHOR_COLUMN));
                share.setLuppeCount(rs.getInt(LUPPE_COUNT_COLUMN));
                share.setDigCount(rs.getInt(DIG_COUNT_COLUMN));
                share.setViewCount(rs.getInt(VIEW_COUNT_COLUMN));
                share.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                share.setShareStatusId(rs.getInt(SHARE_STATUS_ID_COLUMN));
                share.setRssResourceId(rs.getInt(RSS_RESOURCE_ID_COLUMN));
                share.setUserId(rs.getInt(USER_ID_COLUMN));
                share.setLastModifiedDate(rs.getLong(LAST_MODIFIED_DATE_COLUMN));
                share.setResourceName(rs.getString(RESOURCE_NAME_COLUMN));
                share.setCategoryName(rs.getString(CATEGORY_NAME_COLUMN));
                shares.add(share);
            }
            return shares.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Share> mapShareListWithDetails(ResultSet rs) {
        try {
            List<Share> shares = new ArrayList<Share>();
            while (rs.next()) {
                Share share = new Share();
                share.setShareId(rs.getInt(SHARE_ID_COLUMN));
                share.setTitle(rs.getString(TITLE_COLUMN));
                share.setDescription(rs.getString(DESCRIPTION_COLUMN));
                share.setContent(rs.getString(CONTENT_COLUMN));
                share.setUrl(rs.getString(URL_COLUMN));
                share.setAuthor(rs.getString(AUTHOR_COLUMN));
                share.setLuppeCount(rs.getInt(LUPPE_COUNT_COLUMN));
                share.setDigCount(rs.getInt(DIG_COUNT_COLUMN));
                share.setViewCount(rs.getInt(VIEW_COUNT_COLUMN));
                share.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                share.setShareStatusId(rs.getInt(SHARE_STATUS_ID_COLUMN));
                share.setRssResourceId(rs.getInt(RSS_RESOURCE_ID_COLUMN));
                share.setUserId(rs.getInt(USER_ID_COLUMN));
                share.setLastModifiedDate(rs.getLong(LAST_MODIFIED_DATE_COLUMN));
                share.setResourceName(rs.getString(RESOURCE_NAME_COLUMN));
                share.setCategoryName(rs.getString(CATEGORY_NAME_COLUMN));
                shares.add(share);
            }
            return shares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

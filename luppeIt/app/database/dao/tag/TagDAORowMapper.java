package database.dao.tag;

import models.share.Share;
import models.share.ShareTag;
import models.share.ShareTagWithName;
import models.share.Tag;

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
public class TagDAORowMapper {

    public static final String COLUMN_TAG_ID = "t.tag_id";
    public static final String COLUMN_TAG_NAME = "t.tag_name";
    public static final String COLUMN_TAG_STATUS_ID = "t.tag_status_id";
    
    public static final String COLUMN_ST_SHARE_TAG_ID = "st.share_tag_id";
    public static final String COLUMN_ST_SHARE_ID = "st.share_id";
    public static final String COLUMN_ST_TAG_ID = "st.tag_id";
    public static final String COLUMN_ST_TRUTH = "st.truth";
    
    public static List<Tag> mapTagList(ResultSet rs) {
    	try {
    		List<Tag> tags = new ArrayList<Tag>();
    		while (rs.next()) {
    			Tag tag = new Tag();
    			tag.setTagId(rs.getInt(COLUMN_TAG_ID));
    			tag.setTagName(rs.getString(COLUMN_TAG_NAME));
    			tag.setTagStatusId(rs.getInt(COLUMN_TAG_STATUS_ID));
    			tags.add(tag);
    		}
    		return tags;
    	} catch(SQLException e) {
    		return null;
    	}
    }
    
    public static List<String> mapCorrespondingTags(ResultSet rs) {
    	try {
    		List<String> tags = new ArrayList<String>();
    		while (rs.next()) {
    			String tag = rs.getString(COLUMN_TAG_NAME);
    			tags.add(tag);
    		}
    		return tags;
    	} catch(SQLException e) {
    		return null;
    	}
    }
    
    public static Long mapTagId(ResultSet rs) {
    	try {
    		rs.next();
    		return rs.getLong(COLUMN_TAG_ID);
    	} catch (SQLException e) {
    		return null;
		}
    }
    
    public static List<ShareTagWithName> mapShareTagWithNameList(ResultSet rs) {
    	try {
    		List<ShareTagWithName> shareTags = new ArrayList<ShareTagWithName>();
    		while (rs.next()) {
    			ShareTagWithName shareTag = new ShareTagWithName();
    			shareTag.setShareTagId(rs.getInt(COLUMN_ST_SHARE_TAG_ID));
    			shareTag.setShareId(rs.getInt(COLUMN_ST_SHARE_ID));
    			shareTag.setTagId(rs.getInt(COLUMN_ST_TAG_ID));
    			shareTag.setTruth(rs.getInt(COLUMN_ST_TRUTH));
    			shareTag.setTagName(rs.getString(COLUMN_TAG_NAME));
    			shareTags.add(shareTag);
    		}
    		return shareTags;
    	} catch (SQLException e) {
    		return null;
    	}
    }
    
    public static List<ShareTag> mapShareIdTagIdList(ResultSet rs) {
    	try {
    		List<ShareTag> shareTags = new ArrayList<ShareTag>();
    		while (rs.next()) {
    			ShareTag st = new ShareTag();
    			st.setShareId(rs.getInt("share_id"));
    			st.setTagId(rs.getInt("tag_id"));
    			shareTags.add(st);
    		}
    		return shareTags;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

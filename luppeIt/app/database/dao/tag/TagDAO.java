package database.dao.tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.LuppeItConstants;

import models.share.Share;
import models.share.ShareTag;
import models.share.ShareTagWithName;
import models.share.Tag;
import play.db.DB;
import database.dao.share.ShareDAORowMapper;

public class TagDAO {
	
	public static final String QUERY_GET_CORRESPONDING_TAGS = "SELECT t.tag_name FROM tag t WHERE t.tag_name LIKE ? AND t.tag_status_id = ? ORDER BY RAND() LIMIT 15";
	public static final String QUERY_GET_TAGS_OF_SHARE = "SELECT st.share_tag_id,st.share_id,st.tag_id,st.truth,t.tag_name FROM share_tag st INNER JOIN tag t ON st.tag_id = t.tag_id WHERE st.share_id = ? AND t.tag_name NOT LIKE '${lpt}$%' ORDER BY st.truth DESC";
	public static final String QUERY_ADD_TAG = "INSERT INTO tag (tag_name,tag_status_id) VALUES (?,?)";
	public static final String QUERY_GET_TAG_ID_BY_NAME = "SELECT t.tag_id FROM tag t WHERE t.tag_name = ?";
	public static final String QUERY_ADD_TAG_TO_SHARE = "INSERT INTO share_tag (share_id,tag_id,truth) VALUES (?,?,?)";
	public static final String QUERY_UPDATE_SHARE_TAG_INCREASE_TRUTH_BY_ONE = "UPDATE share_tag SET truth = truth + 1 WHERE share_tag_id = ?";
	public static final String QUERY_UPDATE_SHARE_TAG_DECREASE_TRUTH_BY_ONE = "UPDATE share_tag SET truth = truth - 1 WHERE share_tag_id = ?";
	public static final String QUERY_GET_TAGS_OF_SHARES_1 = "SELECT share_id,tag_id FROM share_tag WHERE share_id IN (REPLACE_THIS)";
	
	public static List<String> getCorrespondingTags(String text) {
        try {
        	text = text.toLowerCase();
        	PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_CORRESPONDING_TAGS);
        	ps.setString(1, text + "%");
        	ps.setInt(2, LuppeItConstants.TAG_STATUS_ID_ACTIVE);
        	return TagDAORowMapper.mapCorrespondingTags(ps.executeQuery());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	public static List<ShareTagWithName> getTagsOfShare(Integer shareId) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_TAGS_OF_SHARE);
			ps.setInt(1, shareId);
			return TagDAORowMapper.mapShareTagWithNameList(ps.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Long addTag(String text) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_TAG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, text);
			ps.setInt(2, LuppeItConstants.TAG_STATUS_ID_ACTIVE);
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
    		keys.next();
    		
    		return keys.getLong(1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Long getTagIdOfTag(String text) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_TAG_ID_BY_NAME);
			ps.setString(1, text);
			return TagDAORowMapper.mapTagId(ps.executeQuery());
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static boolean addTagToShare(Long tagId, Integer shareId) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_TAG_TO_SHARE);
			ps.setInt(1, shareId);
			ps.setInt(2, Integer.parseInt(tagId.toString()));
			ps.setInt(3, 0);
			return ps.execute();
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean updateShareTagIncreaseTruthByOne(Integer shareTagId) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_TAG_INCREASE_TRUTH_BY_ONE);
			ps.setInt(1, shareTagId);
			return ps.execute();
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean updateShareTagDecreaseTruthByOne(Integer shareTagId) {
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_SHARE_TAG_DECREASE_TRUTH_BY_ONE);
			ps.setInt(1, shareTagId);
			return ps.execute();
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static Map<Integer, List<Integer>> getTagsOfShares(List<Share> shares) {
		String replaceThis = null;
		for (Share share: shares) {
			if (replaceThis != null) {
				replaceThis += ",'" + share.getShareId() + "'";
			} else {
				replaceThis = "'" + share.getShareId() + "'";
			}
		}
		
		try {
			PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_TAGS_OF_SHARES_1.replace("REPLACE_THIS", replaceThis));
			List<ShareTag> shareTags = TagDAORowMapper.mapShareIdTagIdList(ps.executeQuery());
			
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
			for (ShareTag shareTag: shareTags) {
				if (map.get(shareTag.getShareId()) == null) {
					map.put(shareTag.getShareId(), new ArrayList<Integer>());
					map.get(shareTag.getShareId()).add(shareTag.getTagId());
				} else {
					map.get(shareTag.getShareId()).add(shareTag.getTagId());
				}
			}
			
			return map;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}

package database.dao.resource;

import config.LuppeItConstants;
import models.share.Resource;
import models.share.RssResource;
import models.status.ResourceStatus;
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
public class ResourceDAO {

    public static final String QUERY_GET_ALL_RESOURCES = "SELECT resource_id, resource_name, resource_status_id FROM resource ORDER BY resource_name";
    public static final String QUERY_GET_ALL_RESOURCE_STATUSES = "SELECT resource_status_id, resource_status_name FROM resource_status ORDER BY resource_status_id";
    
    public static List<Resource> getAllResources() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_RESOURCES);
    		return ResourceDAORowMapper.mapResourceList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static List<ResourceStatus> getAllResourceStatuses() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_RESOURCE_STATUSES);
    		return ResourceDAORowMapper.mapResourceStatusList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

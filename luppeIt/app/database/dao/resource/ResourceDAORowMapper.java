package database.dao.resource;

import models.share.Category;
import models.share.Resource;
import models.share.RssResource;
import models.status.ResourceStatus;

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
public class ResourceDAORowMapper {

	public static final String RESOURCE_ID_COLUMN = "resource_id";
	public static final String RESOURCE_NAME_COLUMN = "resource_name";
	public static final String RESOURCE_STATUS_ID_COLUMN = "resource_status_id";
	public static final String RESOURCE_STATUS_NAME_COLUMN = "resource_status_name";
	
    public static List<Resource> mapResourceList(ResultSet rs) {
        try {
        	List<Resource> resources = new ArrayList<Resource>();
        	while (rs.next()) {
        		Resource resource = new Resource();
        		resource.setResourceId(rs.getInt(RESOURCE_ID_COLUMN));
        		resource.setResourceName(rs.getString(RESOURCE_NAME_COLUMN));
        		resource.setResourceStatusId(rs.getInt(RESOURCE_STATUS_ID_COLUMN));
        		resources.add(resource);
        	}
            return resources;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<ResourceStatus> mapResourceStatusList(ResultSet rs) {
    	try {
    		List<ResourceStatus> resourceStatuses = new ArrayList<ResourceStatus>();
    		while (rs.next()) {
    			ResourceStatus resourceStatus = new ResourceStatus();
    			resourceStatus.setResourceStatusId(rs.getInt(RESOURCE_STATUS_ID_COLUMN));
    			resourceStatus.setResourceStatusName(rs.getString(RESOURCE_STATUS_NAME_COLUMN));
    			resourceStatuses.add(resourceStatus);
    		}
    		return resourceStatuses;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

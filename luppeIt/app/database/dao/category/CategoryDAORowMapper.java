package database.dao.category;

import models.share.Category;
import models.status.CategoryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/28/12
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAORowMapper {

    public static final String CATEGORY_ID_COLUMN = "category_id";
    public static final String CATEGORY_NAME_COLUMN = "category_name";
    public static final String CATEGORY_STATUS_ID_COLUMN = "category_status_id";
    
    public static final String CATEGORY_STATUS_NAME_COLUMN = "category_status_name";

    public static List<Category> mapCategoryList(ResultSet rs) {
        try {
            List<Category> categories = new ArrayList<Category>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt(CATEGORY_ID_COLUMN));
                category.setCategoryName(rs.getString(CATEGORY_NAME_COLUMN));
                category.setCategoryStatusId(rs.getInt(CATEGORY_STATUS_ID_COLUMN));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<CategoryStatus> mapCategoryStatusList(ResultSet rs) {
    	try {
    		List<CategoryStatus> categoryStatuses = new ArrayList<CategoryStatus>();
    		while(rs.next()) {
    			CategoryStatus categoryStatus = new CategoryStatus();
    			categoryStatus.setCategoryStatusId(rs.getInt(CATEGORY_STATUS_ID_COLUMN));
    			categoryStatus.setCategoryStatusName(rs.getString(CATEGORY_STATUS_NAME_COLUMN));
    			categoryStatuses.add(categoryStatus);
    		}
    		return categoryStatuses;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}

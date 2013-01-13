package database.dao.category;

import models.share.Category;
import models.share.Resource;
import models.status.CategoryStatus;
import play.db.DB;
import play.db.jpa.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import config.LuppeItConstants;

/**
 * Created with IntelliJ IDEA.
 * User: farukkuscan
 * Date: 11/21/12
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAO {

    public static final String QUERY_GET_ALL_CATEGORIES_ORDER_BY_NAME = "SELECT category_id, category_name, category_status_id FROM category ORDER BY category_name ASC";
    public static final String QUERY_ADD_CATEGORY = "INSERT INTO category (category_name, category_status_id) VALUES (?,?)";
    public static final String QUERY_UPDATE_CATEGORY = "UPDATE category SET category_name = ?, category_status_id = ? WHERE category_id = ?";
    public static final String QUERY_DELETE_CATEGORY = "DELETE FROM category WHERE category_id = ?";
    public static final String QUERY_GET_ALL_CATEGORY_STATUSES = "SELECT category_status_id, category_status_name FROM category_status";
    public static final String QUERY_GET_CATEGORY_BY_CATEGORY_ID = "SELECT category_id, category_name, category_status_id FROM category WHERE category_id = ?";
    public static final String QUERY_ADD_USER_CATEGORY = "INSERT INTO user_category (user_id,category_id) VALUES (?,?)";
    public static final String QUERY_GET_ACTIVE_USER_CATEGORIES = "SELECT category_id, category_name, category_status_id FROM category WHERE category_id IN (SELECT category_id FROM user_category WHERE user_id = ?) AND category_status_id = ? ORDER BY category_name";
    public static final String QUERY_DELETE_USER_CATEGORIES = "DELETE FROM user_category WHERE user_id = ?";
    
    public static List<Category> getAllCategoriesOrderByName() {
        return CategoryDAORowMapper.mapCategoryList(DB.executeQuery(QUERY_GET_ALL_CATEGORIES_ORDER_BY_NAME));
    }
    
    public static Category getCategoryByCategoryId(Integer categoryId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_CATEGORY_BY_CATEGORY_ID);
    		ps.setInt(1, categoryId);
    		return CategoryDAORowMapper.mapCategoryList(ps.executeQuery()).get(0);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static boolean addCategory(Category category) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_CATEGORY);
    		ps.setString(1, category.getCategoryName());
    		ps.setInt(2, category.getCategoryStatusId());
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean updateCategory(Category category) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_UPDATE_CATEGORY);
    		ps.setString(1, category.getCategoryName());
    		ps.setInt(2, category.getCategoryStatusId());
    		ps.setInt(3, category.getCategoryId());
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }

    public static boolean deleteCategory(Integer categoryId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_DELETE_CATEGORY);
    		ps.setInt(1, categoryId);
    		return ps.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static List<CategoryStatus> getAllCategoryStatuses() {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ALL_CATEGORY_STATUSES);
    		return CategoryDAORowMapper.mapCategoryStatusList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static void addUserCategories(Long userId, List<Integer> userCategories) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_ADD_USER_CATEGORY);
    		for (Integer userCategoryId: userCategories) {
    			ps.setLong(1, userId);
    			ps.setInt(2, userCategoryId);
    			ps.addBatch();
    		}
    		ps.executeBatch();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    @Transactional
    public static void updateUserCategories(Long userId, List<Integer> userCategories) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_DELETE_USER_CATEGORIES);
    		ps.setLong(1, userId);
    		ps.execute();
    		
    		addUserCategories(userId, userCategories);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static List<Category> getActiveUserCategories(Integer userId) {
    	try {
    		PreparedStatement ps = DB.getConnection().prepareStatement(QUERY_GET_ACTIVE_USER_CATEGORIES);
    		ps.setInt(1, userId);
    		ps.setInt(2, LuppeItConstants.CATEGORY_STATUS_ACTIVE);
    		return CategoryDAORowMapper.mapCategoryList(ps.executeQuery());
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}

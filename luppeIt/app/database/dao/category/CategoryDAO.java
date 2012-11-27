package database.dao.category;

import models.share.Category;
import play.db.DB;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: farukkuscan
 * Date: 11/21/12
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAO {

    public static final String QUERY_GET_ALL_CATEGORIES_ORDER_BY_NAME = "SELECT category_id, category_name, category_status_id FROM category ORDER BY category_name ASC";

    public static List<Category> getAllCategoriesOrderByName() {
        return CategoryDAORowMapper.mapCategoryList(DB.executeQuery(QUERY_GET_ALL_CATEGORIES_ORDER_BY_NAME));
    }

}

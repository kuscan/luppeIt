package database.dao;

import models.share.Category;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: farukkuscan
 * Date: 11/21/12
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAO {

    public static List<Category> getAllCategories() {
        return Category.findAll();
    }

}

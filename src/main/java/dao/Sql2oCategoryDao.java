package dao;

import models.Category;
import models.Task;

import java.util.List;

/**
 * Created by Guest on 8/14/17.
 */
public class Sql2oCategoryDao implements CategoryDao {
    @Override
    public void add(Category category) {

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public List<Task> getAllTasksByCategory(int categoryId) {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllCategories() {

    }
}

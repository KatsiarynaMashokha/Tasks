package dao;

import models.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.Assert.*;

public class Sql2oTaskDaoTest {

    private Sql2oTaskDao taskDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        taskDao = new Sql2oTaskDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Task task = new Task ("mow the lawn", 5);
        int originalTaskId = task.getId();
        taskDao.add(task);
        assertNotEquals(originalTaskId, task.getId()); //how does this work?
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Task task = setupNewTask();
        taskDao.add(task); //add to dao (takes care of saving)
        Task foundTask = taskDao.findById(task.getId()); //retrieve
        assertEquals(task, foundTask); //should be the same
    }

    @Test
    public void getAll_AllTasksFound_True() {
        Task taskOne = new Task("mow the lawn", 5);
        Task taskTwo = new Task("mow the", 5);
        Task taskThree = new Task("mow", 5);
        taskDao.add(taskOne);
        taskDao.add(taskTwo);
        taskDao.add(taskThree);
        assertEquals(3, taskDao.getAll().size());
    }

    @Test
    public void noTestsReturned_True(){
        Task taskOne = setupNewTask();
        Task foundByIdTask = taskDao.findById(taskOne.getId());
        assertNotEquals(taskOne, foundByIdTask);
    }

    @Test
    public void noTasksReturnsEmptyList() throws Exception {
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void update_TaskUpdatesCorrectly() throws Exception {
        Task task = setupNewTask();
        taskDao.add(task);
        taskDao.update(task.getId(), "Hello", 5);
        Task updatedTask = taskDao.findById(task.getId());
        System.out.println("the desc: " + task.getDescription() + " id: " + task.getId());
        System.out.println("the desc: " + updatedTask.getDescription() + " id: " + updatedTask.getId());
        assertEquals("Hello", updatedTask.getDescription());
    }

    @Test
    public void delete_Task_Correctly() throws Exception {
        Task task = setupNewTask();
        taskDao.add(task);
        taskDao.deleteById(task.getId());
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void clearAll_AllTasksAreDeleted_returns0() {
        Task taskOne = setupNewTask();
        Task taskTwo = new Task("mow the", 5);
        taskDao.add(taskOne);
        taskDao.add(taskTwo);
        taskDao.clearAllTasks();
        assertEquals(0, taskDao.getAll().size());
    }


    public Task setupNewTask(){
        return new Task("mow the lawn",1);
    }


    @Test
    public void categoryIdIsReturnedCorrectly() throws Exception {
        Task task = setupNewTask();
        int originalCatId = task.getCategoryId();
        taskDao.add(task);
        assertEquals(originalCatId, taskDao.findById(task.getId()).getCategoryId());
    }

}


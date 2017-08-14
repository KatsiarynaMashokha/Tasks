package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void categoryMethodInstantiates_Correctly() {
        Category testCat = new Category("cat");
        assertEquals(true, testCat instanceof Category);
    }

    @Test
    public void getName_NameGetIs_True() {
        Category testCat = new Category("test");
        assertEquals("test", testCat.getName());

    }

    @Test
    public void getIdValue_5() {
        Category testCat = new Category("object");
        testCat.setId(5);
        assertEquals(5, testCat.getId());
    }


}
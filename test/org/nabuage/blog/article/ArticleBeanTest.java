package org.nabuage.blog.article;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.nabuage.blog.helper.TomEEContainer;
import org.nabuage.blog.persistence.ArticleEntity;
import org.nabuage.blog.persistence.UserEntity;
import org.nabuage.blog.user.UserBean;

/**
 *
 * @author George
 */
public class ArticleBeanTest {
    
    private ArticleBean articleBean;
    private String title;
    private String text;
    private Long authorId;
    
    public ArticleBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException {        
        UserBean userBean = (UserBean)TomEEContainer.getInstance().getEJBContainerContextLookup("UserBean");
        userBean.create("junit@nabuage.org", "junit", "junit", UserEntity.Type.USER, "JUnit", "JUnit", "JUnit");
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException, SQLException {
        DataSource dataSource = TomEEContainer.getInstance().getDataSource();
        dataSource.getConnection().createStatement().execute("DELETE FROM article");
        dataSource.getConnection().createStatement().execute("DELETE FROM userprofile");
        dataSource.getConnection().createStatement().execute("UPDATE sequencetable SET sequencecount = 1");
    }
    
    @Before
    public void setUp() throws NamingException {
        articleBean = (ArticleBean)TomEEContainer.getInstance().getEJBContainerContextLookup("ArticleBean");
        
        title = "Blog Title - Unit Test";
        text = "Blog Text - Unit Test";
        authorId = (long) 1;
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of create method, of class ArticleBean.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        assertEquals(true, result != null && result.getId() != null && result.getDateAdded() != null);
        
    }

    /**
     * Test of update method, of class ArticleBean.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
                
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        
        title = "Update Blog Title - Unit Test";
        text = "Update Blog Text - Unit Test";
        
        result.setTitle(title);
        result.setText(text);
        
        result = articleBean.update(result.getId(), title, text);
        
        assertEquals(true, result != null && result.getTitle().equals(title) && result.getText().equals(text) && result.getDateModified() != null);
        
    }

    /**
     * Test of delete method, of class ArticleBean.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        Long deleteId;
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        deleteId = result.getId();
        
        articleBean.delete(deleteId);
        
        result = articleBean.findById(deleteId);
        
        assertEquals(true, result == null);
        
    }

    
    /**
     * Test of findById method, of class ArticleBean.
     */
    //@Test
    public void xtestFindById() throws Exception {
        System.out.println("findById");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        
        result = articleBean.findById(result.getId());
        
        assertEquals(true, result != null);
        
    }

    /**
     * Test of findByType method, of class ArticleBean.
     */
    @Test
    public void testFindByType() throws Exception {
        System.out.println("findByType");
        
        articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        
        List<ArticleEntity> results = articleBean.findByType(ArticleEntity.Type.BLOG);
        
        assertEquals(true, results != null && !results.isEmpty());
        
    }

    /**
     * Test of published method, of class ArticleBean.
     */
    @Test
    public void testPublished() throws Exception {
        System.out.println("published");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        articleBean.published(result.getId());
        
        result = articleBean.findById(result.getId());
        
        assertEquals(true, result != null && result.getStatus() == ArticleEntity.Status.PUBLISHED && result.getDatePublished() != null);
        
    }

    /**
     * Test of unpublished method, of class ArticleBean.
     */
    @Test
    public void testUnpublished() throws Exception {
        System.out.println("unpublished");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        articleBean.unpublished(result.getId());
        
        result = articleBean.findById(result.getId());
        
        assertEquals(true, result != null && result.getStatus()== ArticleEntity.Status.DRAFT && result.getDatePublished() == null);
        
    }

    /**
     * Test of findByURL method, of class ArticleBean.
     */
    @Test
    public void testFindByURL() throws Exception {
        System.out.println("findByURL");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        
        result = articleBean.findByURL(result.getUrl());
        
        assertEquals(true, result != null);
    }
    
    /**
     * 
     */
    @Test
    public void testCreateDuplicateURL() throws Exception {
        System.out.println("createDuplicateURL");
        
        ArticleEntity result = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        System.out.println(result.getUrl());
        ArticleEntity duplicateResult = articleBean.create(title, text, authorId, ArticleEntity.Type.BLOG);
        System.out.println(duplicateResult.getUrl());
        assertEquals(false, result.getUrl().equals(duplicateResult.getUrl()));
    }
    
}

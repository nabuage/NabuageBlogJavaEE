package org.nabuage.blog.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.nabuage.blog.article.ArticleLocalService;
import org.nabuage.blog.persistence.ArticleEntity;
import org.nabuage.blog.persistence.ArticleEntity.Type;
import org.nabuage.blog.rest.utility.RestConverter;

/**
 * REST Web Service
 *
 * @author George
 */
@Path("article")
public class ArticleResource {

    @Context
    private UriInfo context;
    
    @EJB
    private ArticleLocalService articleService;

    /**
     * Creates a new instance of ArticleResource
     */
    public ArticleResource() {
    }

    /**
     * Retrieves representation of an instance of org.nabuage.blog.rest.ArticleResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getArticleById(@PathParam("id") Long id) {
        ArticleEntity articleEntity = articleService.findById(id);
        return Response.ok(new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getText(), articleEntity.getDateAdded(), articleEntity.getDateModified(), articleEntity.getDatePublished(), articleEntity.getAuthorId())).build();
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("admin")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        List<Article> articles = RestConverter.toArticles(articleService.findByType(ArticleEntity.Type.BLOG));
        GenericEntity<List<Article>> genericEntities = new GenericEntity<List<Article>>(articles) {};
        return Response.ok(genericEntities).build();
    }
    
    @GET
    @Path("type/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByType(@PathParam("type") String type) {
        Type articleType = null;
        
        if (type.equalsIgnoreCase("about")) {
            articleType = ArticleEntity.Type.ABOUT;
        }
        else if (type.equalsIgnoreCase("etc")) {
            articleType = ArticleEntity.Type.PROJECT;
        }
        else {
            articleType = ArticleEntity.Type.BLOG;
        }
        
        List<Article> articles = RestConverter.toArticles(articleService.findByType(articleType));
        GenericEntity<List<Article>> genericEntities = new GenericEntity<List<Article>>(articles) {};
        return Response.ok(genericEntities).build();
    }
}

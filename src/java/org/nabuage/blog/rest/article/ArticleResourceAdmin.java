package org.nabuage.blog.rest.article;

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
@Path("admin/article")
public class ArticleResourceAdmin extends ArticleResource {

    @Context
    private UriInfo context;
    
    @EJB
    private ArticleLocalService articleService;

    /**
     * Creates a new instance of ArticleResource
     */
    public ArticleResourceAdmin() {
    }
    
    /**
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        List<Article> articles = RestConverter.toArticles(articleService.findByType(ArticleEntity.Type.BLOG));
        GenericEntity<List<Article>> genericEntities = new GenericEntity<List<Article>>(articles) {};
        return Response.ok(genericEntities).build();
    }
    
}

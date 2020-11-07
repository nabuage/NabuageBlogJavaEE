package org.nabuage.blog.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author George
 */
@javax.ws.rs.ApplicationPath("services")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.nabuage.blog.rest.ArticleResource.class);
        resources.add(org.nabuage.blog.rest.UserRest.class);
        resources.add(org.nabuage.blog.rest.article.ArticleResource.class);
        resources.add(org.nabuage.blog.rest.article.ArticleResourceAdmin.class);
        resources.add(org.nabuage.blog.rest.message.ContactMessageResource.class);
        resources.add(org.nabuage.blog.rest.user.UserRest.class);
    }
    
}

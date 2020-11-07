package org.nabuage.blog.rest.user;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.nabuage.blog.persistence.UserEntity;
import org.nabuage.blog.user.UserLocalService;

/**
 * REST Web Service
 *
 * @author George
 */
@Path("user")
public class UserRest {

    @Context
    private UriInfo context;
    
    @EJB
    private UserLocalService userService;

    /**
     * Creates a new instance of UserRest
     */
    public UserRest() {
    }

    /**
     * Retrieves a User instance by Id.
     * @return an instance of org.nabuage.blog.rest.User
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id") Long id) {
        UserEntity userEntity = userService.findById(id);
        
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        
        return user;
    }
    /*@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("id") Long id) {
        org.nabuage.blog.user.User userTO = userService.findById(id);
        
        User user = new User();
        user.setId(userTO.getId());
        user.setFirstName(userTO.getFirstName());
        user.setLastName(userTO.getLastName());
        
        return Response.ok(user).build();
    }*/
    
    /**
     * Creates a User.
     * @return an instance of org.nabuage.blog.rest.User
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        
        return null;
    }
    
    /**
     * Updates a User.
     * @return an instance of org.nabuage.blog.rest.User
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User user) {
        
        return null;
    }
}

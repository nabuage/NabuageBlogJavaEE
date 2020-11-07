package org.nabuage.blog.rest.message;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.nabuage.blog.message.ContactMessageLocalService;
import org.nabuage.blog.persistence.ContactMessageEntity;

/**
 *
 * @author George
 */
@Path("contactmessage")
public class ContactMessageResource {
    
    private static final Logger LOGGER = Logger.getLogger(ContactMessageResource.class.getName());
    
    @EJB
    private ContactMessageLocalService contactMessageService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ContactMessage contactMessage) {
        
        ContactMessageEntity contactMessageEntity = contactMessageService.create(contactMessage.getEmailAddress(), contactMessage.getName(), contactMessage.getMessage());
        contactMessage.setId(contactMessageEntity.getId());
        
        return Response.ok(contactMessage).build();
    }
    
}

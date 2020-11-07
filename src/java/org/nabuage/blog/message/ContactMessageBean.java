package org.nabuage.blog.message;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.nabuage.blog.persistence.ContactMessageEntity;
import org.nabuage.blog.persistence.GenericPersistenceService;
import org.nabuage.blog.rest.utility.Guid;

/**
 *
 * @author George
 */
@Stateless
@Local(ContactMessageLocalService.class)
@Remote(ContactMessageRemoteService.class)
@LocalBean
public class ContactMessageBean implements ContactMessageGenericService, ContactMessageLocalService, ContactMessageRemoteService {
    
    private static final Logger LOGGER = Logger.getLogger(ContactMessageBean.class.getName());
    
    @EJB
    private GenericPersistenceService persistenceService;
    
    @Override
    public ContactMessageEntity create(String emailAddress, String name, String message) {
        ContactMessageEntity contactMessageEntity = new ContactMessageEntity();
        contactMessageEntity.setEmailAddress(emailAddress);
        contactMessageEntity.setName(name);
        contactMessageEntity.setMessage(message);
        contactMessageEntity.setGuid(Guid.getInstance().create());
        contactMessageEntity.setDateAdded(new Date());
        
        persistenceService.create(contactMessageEntity);
        
        return contactMessageEntity;
    }
    
}

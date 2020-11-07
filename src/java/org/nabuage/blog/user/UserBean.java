package org.nabuage.blog.user;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.nabuage.blog.exception.InvalidPasswordException;
import org.nabuage.blog.persistence.GenericPersistenceService;
import org.nabuage.blog.persistence.UserEntity;
import org.nabuage.blog.utility.Password;

/**
 *
 * @author George
 */
@Stateless
@Local(UserLocalService.class)
@Remote(UserRemoteService.class)
@LocalBean
public class UserBean implements UserGenericService, UserLocalService, UserRemoteService {
    
    private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());
    
    @EJB
    private GenericPersistenceService persistenceService;

    @Override
    public UserEntity create(String emailAddress, String username, String password, UserEntity.Type type, String firstName, String lastName, String displayName) {
        UserEntity user = new UserEntity();
        
        Password passwordSalt = new Password();        
        user.setSalt(passwordSalt.generateSalt());        
        user.setPassword(passwordSalt.saltPassword(password, user.getSalt()));        
        
        user.setEmailAddress(emailAddress);
        user.setUsername(username);        
        user.setType(type);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDisplayName(displayName);
        user.setDateAdded(new Date());
        return persistenceService.create(user);
    }

    @Override
    public UserEntity update(Long id, String emailAddress, String username, UserEntity.Type type, String firstName, String lastName, String displayName) {
        UserEntity user = findById(id);
        
        if (user != null && user.getDateDeleted() == null) {
            user.setEmailAddress(emailAddress);
            user.setUsername(username);
            user.setType(type);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDisplayName(displayName);
            user.setDateModified(new Date());
            return persistenceService.update(user);
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        UserEntity user = persistenceService.find(UserEntity.class, id);
        
        if (user != null && user.getDateDeleted() == null) {
            user.setDateDeleted(new Date());
            persistenceService.update(user);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity user = persistenceService.find(UserEntity.class, id);
        
        if (user != null && user.getDateDeleted() == null) {
            return user;
        }
        else {
            return null;
        }
    }    

    /**
     *
     * @param id
     * @param currentPassword
     * @param newPassword
     * @return
     * @throws InvalidPasswordException
     */
    @Override
    public UserEntity updatePassword(Long id, String currentPassword, String newPassword) throws InvalidPasswordException {
        UserEntity user = findById(id);
        
        if (user != null && user.getDateDeleted() == null) {
            
            if (!newPassword.equals("")) {
                Password passwordSalt = new Password();
            
                if (passwordSalt.valid(currentPassword, user.getPassword(), user.getSalt())) {
                    user.setSalt(passwordSalt.generateSalt());        
                    user.setPassword(passwordSalt.saltPassword(newPassword, user.getSalt()));

                    user.setDateModified(new Date());

                    return persistenceService.update(user);
                }
                else {
                    throw new InvalidPasswordException("Invalid Password.");
                }                
            }
            else {
                throw new InvalidPasswordException("New password is empty.");
            }            
        }
        else {
            return null;
        }
    }
}

package org.nabuage.blog.user;

import org.nabuage.blog.exception.InvalidPasswordException;
import org.nabuage.blog.persistence.UserEntity;


/**
 *
 * @author George
 */
public interface UserGenericService {
    public UserEntity create(String emailAddress, String username, String password, UserEntity.Type type, String firstName, String lastName, String displayName);
    public UserEntity update(Long id, String emailAddress, String username, UserEntity.Type type, String firstName, String lastName, String displayName);
    public void delete(Long id);
    public UserEntity findById(Long id); 
    
    public UserEntity updatePassword(Long id, String currentPassword, String newPassword)  throws InvalidPasswordException;
}

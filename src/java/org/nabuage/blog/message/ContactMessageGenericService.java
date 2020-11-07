package org.nabuage.blog.message;

import org.nabuage.blog.persistence.ContactMessageEntity;

/**
 *
 * @author George
 */
public interface ContactMessageGenericService {
    public ContactMessageEntity create(String emailAddress, String name, String message);
}

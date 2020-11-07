package org.nabuage.blog.persistence;

import java.io.Serializable;

/**
 *
 * @author george
 */
public interface PersistentEntity<PK extends Serializable> extends Serializable {
    PK getId();
}

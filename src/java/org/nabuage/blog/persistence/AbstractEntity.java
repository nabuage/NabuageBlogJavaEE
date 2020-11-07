package org.nabuage.blog.persistence;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author george
 */
@MappedSuperclass
public abstract class AbstractEntity implements PersistentEntity<Long> {

    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();


}

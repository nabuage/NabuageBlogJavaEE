package org.nabuage.blog.persistence;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author George
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GenericPersistenceService {
    @PersistenceContext(name="BlogRestPU")
    EntityManager em;
    
    public <T> T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }
    
    public <T> T find(Class<T> type, Object id) {
        return (T)this.em.find(type, id);
    }
    
    public void delete(Class type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }
    
    public <T> T update(T t) {
        return (T)this.em.merge(t);
    }
    
    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }
    
    public List findWithNamedQuery(String namedQueryName, int resultLimit) {
        return this.em.createNamedQuery(namedQueryName).setMaxResults(resultLimit).getResultList();
    }
    
    public <T> List<T> findByNativeQuery(String sql, Class<T> type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }
    
    public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters, int resultLimit) {
        Set<Entry<String,Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        
        for (Entry<String,Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
    public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }
    
    public void execute(String sql) {
        em.createNativeQuery(sql);
    }
    
}

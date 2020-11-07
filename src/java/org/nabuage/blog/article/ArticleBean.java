package org.nabuage.blog.article;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.nabuage.blog.persistence.ArticleEntity;
import org.nabuage.blog.persistence.GenericPersistenceService;
import org.nabuage.blog.utility.Text;

/**
 *
 * @author George
 */
@Stateless
@Local(ArticleLocalService.class)
@Remote(ArticleRemoteService.class)
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ArticleBean implements ArticleGenericService, ArticleLocalService, ArticleRemoteService {
    
    private static final Logger LOGGER = Logger.getLogger(ArticleBean.class.getName());
    
    @EJB
    private GenericPersistenceService persistenceService;
    
    @Override
    public ArticleEntity create(String title, String text, Long authorId, ArticleEntity.Type type) {
        String url = title.toLowerCase().replaceAll("[^\\w]", "-");
        boolean urlIsUnique = false;
        
        while (!urlIsUnique) {
            Map<String,Object> parameters = new HashMap<String, Object>();
            parameters.put("url", url);

            List<ArticleEntity> articles = persistenceService.findWithNamedQuery(ArticleEntity.QUERY_BY_URL, parameters);

            if (articles.size() > 0) {
                //ArticleEntity articleEntityURL = articles.get(articles.size()-1);
                url = Text.getInstance().incrementLastNumber(url, "-");
            }
            else {
                urlIsUnique = true;
            }
        }
        
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(title);
        articleEntity.setText(text);
        articleEntity.setUrl(url);
        articleEntity.setAuthorId(authorId);
        articleEntity.setTypeId(type);
        articleEntity.setStatus(ArticleEntity.Status.DRAFT);
        articleEntity.setDateAdded(new Date());
        
        persistenceService.create(articleEntity);        
        System.out.println(articleEntity.getId());
        return articleEntity;
    }

    @Override
    public ArticleEntity update(Long id, String title, String text) {
        String url = title.toLowerCase().replaceAll("[^a-zA-Z0-9]", "-");
        boolean urlIsUnique = false;
        
        ArticleEntity articleEntity = persistenceService.find(ArticleEntity.class, id);
        
        while (!urlIsUnique) {
            Map<String,Object> parameters = new HashMap<String, Object>();
            parameters.put("url", url);

            List<ArticleEntity> articles = persistenceService.findWithNamedQuery(ArticleEntity.QUERY_BY_URL, parameters);            

            if (articles.size() > 0) {
                ArticleEntity articleEntityURL = articles.get(articles.size()-1);
                if (!articleEntityURL.getId().equals(articleEntity.getId())) {            
                    url = Text.getInstance().incrementLastNumber(articleEntityURL.getUrl(), "-");
                }
                else {
                    urlIsUnique = true;
                }
            }
            else {
                urlIsUnique = true;
            }
        }
        
        
        
        articleEntity.setTitle(title);
        articleEntity.setText(text);
        articleEntity.setUrl(url);
        articleEntity.setDateModified(new Date());
                
        persistenceService.update(articleEntity);
        
        return articleEntity;
    }

    @Override
    public void delete(Long id) {
        ArticleEntity articleEntity = persistenceService.find(ArticleEntity.class, id);
        
        if (articleEntity != null && articleEntity.getDateDeleted() == null) {
            articleEntity.setDateDeleted(new Date());
            persistenceService.update(articleEntity);
        }
    }

    @Override
    public ArticleEntity findById(Long id) {
        ArticleEntity articleEntity = persistenceService.find(ArticleEntity.class, id);
        
        if (articleEntity != null && articleEntity.getDateDeleted() == null) {
            return articleEntity;
        }
        else {
            return null;
        }
    }

    @Override
    public List<ArticleEntity> findByType(ArticleEntity.Type type) {
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("type", type.getValue());
        return persistenceService.findWithNamedQuery(ArticleEntity.QUERY_BY_TYPE_ID, parameters);
    }

    @Override
    public void published(Long id) {
        ArticleEntity articleEntity = findById(id);
        
        if (articleEntity != null) {
            articleEntity.setDatePublished(new Date());
            articleEntity.setDateModified(new Date());
            articleEntity.setStatus(ArticleEntity.Status.PUBLISHED);
            persistenceService.update(articleEntity);
        }
    }

    @Override
    public void unpublished(Long id) {
        ArticleEntity articleEntity = findById(id);
        
        if (articleEntity != null) {
            articleEntity.setDatePublished(null);
            articleEntity.setDateModified(new Date());
            articleEntity.setStatus(ArticleEntity.Status.DRAFT);
            persistenceService.update(articleEntity);
        }   
    }

    @Override
    public ArticleEntity findByURL(String url) {
        ArticleEntity articleEntity = null;
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("url", url);
        
        List<ArticleEntity> articles = persistenceService.findWithNamedQuery(ArticleEntity.QUERY_BY_URL, parameters);
        
        if (articles.size() > 0) {
            articleEntity = articles.get(articles.size() - 1);
        }
        
        return articleEntity;
    }
    
}

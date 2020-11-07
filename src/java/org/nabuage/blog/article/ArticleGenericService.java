package org.nabuage.blog.article;

import java.util.List;
import org.nabuage.blog.persistence.ArticleEntity;

/**
 *
 * @author George
 */
public interface ArticleGenericService {
    public ArticleEntity create(String title, String text, Long authorId, ArticleEntity.Type type);
    public ArticleEntity update(Long id, String title, String text);
    public void delete(Long id);
    public ArticleEntity findById(Long id);
    public ArticleEntity findByURL(String url);
    public List<ArticleEntity> findByType(ArticleEntity.Type type);
    public void published(Long id);
    public void unpublished(Long id);
}

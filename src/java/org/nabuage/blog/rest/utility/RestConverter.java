package org.nabuage.blog.rest.utility;

import java.util.ArrayList;
import java.util.List;
import org.nabuage.blog.persistence.ArticleEntity;
import org.nabuage.blog.rest.article.Article;

/**
 *
 * @author George
 */
public class RestConverter {
    
    public static Article toArticle(ArticleEntity articleEntity) {
        
        if (articleEntity != null) {
            return new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getText(), articleEntity.getDateAdded(), articleEntity.getDateModified(), articleEntity.getDatePublished(), articleEntity.getAuthorId());
        }
        else {
            return null;
        }        
        
    }
    
    public static ArticleEntity toArticleEntity(Article article) {
        
        if (article != null) {
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setId(article.getId());
            articleEntity.setTitle(article.getTitle());
            articleEntity.setText(article.getText());
            articleEntity.setDateAdded(article.getDateAdded());
            articleEntity.setDateModified(article.getDateModified());
            articleEntity.setDatePublished(article.getDatePublished());
            articleEntity.setAuthorId(article.getAuthorId());
            
            return articleEntity;
        }
        else {
            return null;
        }
        
    }
    
    public static List<Article> toArticles(List<ArticleEntity> articleEntities) {
        
        if (articleEntities != null) {
            
            List<Article> articles = new ArrayList<Article>();
            
            for (ArticleEntity articleEntity : articleEntities) {
                articles.add(new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getText(), articleEntity.getDateAdded(), articleEntity.getDateModified(), articleEntity.getDatePublished(), articleEntity.getAuthorId()));
            }
            
            return articles;            
        }
        else {
            return null;
        }
        
    }
    
    public static List<ArticleEntity> toArticleEntities(List<Article> articles) {
        
        if (articles != null) {
            
            List<ArticleEntity> articleEntities = new ArrayList<ArticleEntity>();
            
            for (Article article : articles) {
                ArticleEntity articleEntity = new ArticleEntity();
                articleEntity.setId(article.getId());
                articleEntity.setTitle(article.getTitle());
                articleEntity.setText(article.getText());
                articleEntity.setDateAdded(article.getDateAdded());
                articleEntity.setDateModified(article.getDateModified());
                articleEntity.setDatePublished(article.getDatePublished());
                articleEntity.setAuthorId(article.getAuthorId());
                
                articleEntities.add(articleEntity);
            }
            
            return articleEntities;
            
            
        }
        else {
            
        }
        
        return null;
    }
    
}

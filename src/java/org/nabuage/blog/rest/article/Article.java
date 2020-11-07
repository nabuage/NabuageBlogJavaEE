package org.nabuage.blog.rest.article;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@XmlRootElement
public class Article {
    private Long id;
    private String title;
    private String text;
    private Date datePublished;
    private Date dateAdded;
    private Date dateModified;
    private Long authorId;
    
    public Article() {
        
    }
    
    public Article(Long id, String title, String text, Date dateAdded, Date dateModified, Date datePublished, Long authorId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.datePublished = datePublished;
        this.authorId = authorId;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the datePublished
     */
    public Date getDatePublished() {
        return datePublished;
    }

    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @return the modified
     */
    public Date getDateModified() {
        return dateModified;
    }

    /**
     * @return the author
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param datePublished the datePublished to set
     */
    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @param dateModified the dateModified to set
     */
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    
    
}

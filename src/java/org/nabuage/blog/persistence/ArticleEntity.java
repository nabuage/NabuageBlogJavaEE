package org.nabuage.blog.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author George
 */
@Entity
@Table(name = "article")
@NamedQueries({
    @NamedQuery(name = ArticleEntity.QUERY_BY_ID, query = "SELECT a FROM ArticleEntity a WHERE a.id = :id AND a.dateDeleted IS NULL"),
    @NamedQuery(name = ArticleEntity.QUERY_BY_TYPE_ID, query = "SELECT a FROM ArticleEntity a WHERE a.type = :type AND a.dateDeleted IS NULL"),
    @NamedQuery(name = ArticleEntity.QUERY_BY_URL, query = "SELECT a FROM ArticleEntity a WHERE a.url = :url AND a.dateDeleted IS NULL"),
})
public class ArticleEntity extends AbstractEntity implements Serializable {
    public final static String QUERY_BY_TYPE_ID = "org.nabuage.blog.persistence.ArticleEntity.BY_TYPE_ID";
    public final static String QUERY_BY_ID = "org.nabuage.blog.persistence.ArticleEntity.BY_ID";
    public final static String QUERY_BY_URL = "org.nabuage.blog.persistence.ArticleEntity.BY_URL";
    
    @TableGenerator(name="articleidsequence",table="sequencetable",pkColumnName="sequencename",valueColumnName="sequencecount",pkColumnValue="articleid",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="articleidsequence")
    @Id
    @Basic(optional = false)
    @Column(name = "articleid")
    private Long id;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "text")
    private String text;
    
    @Basic(optional = false)
    @Column(name = "typeid")
    private int type;

    @Basic(optional = false)
    @Column(name = "dateadded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    
    @Column(name = "datemodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @Column(name = "datedeleted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeleted;
    
    @Column(name = "datepublished")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublished;

    @Basic(optional = false)
    @Column(name = "statusid")
    private int status;
    
    @Basic(optional = false)
    @Column(name = "authorid")
    private Long authorId;
    
    @Column(name = "url")
    private String url;

    public ArticleEntity() {
    }

    public ArticleEntity(Long id) {
        this.id = id;
    }

    public ArticleEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public ArticleEntity(String title, String text, Status status) {
        this.title = title;
        this.text = text;
        this.status = status.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Type getTypeId() {
        return Type.parse(type);
    }

    public void setTypeId(Type type) {
        this.type = type.getValue();
    }
    
    public Status getStatus() {
        return Status.parse(status);
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }
    
    /**
     * @return the authorId
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the datePublished
     */
    public Date getDatePublished() {
        return datePublished;
    }

    /**
     * @param datePublished the datePublished to set
     */
    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticleEntity)) {
            return false;
        }
        ArticleEntity other = (ArticleEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.nabuage.blog.persistence.ArticleEntity[id=" + id + "]";
    }
    
    public static enum Status {
        DRAFT(1),
        PUBLISHED(2);
        
        private final int value;
        
        Status(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public static Status parse(int id) {
            Status status = null;
            for (Status item : Status.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }

            return status;
        }
    }
    
    public static enum Type {
        BLOG(1),
        ABOUT(2),
        PROJECT(3);
        
        private final int value;
        
        Type(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public static Type parse(int id) {
            Type type = null;
            for (Type item : Type.values()) {
                if (item.getValue() == id) {
                    type = item;
                    break;
                }
            }

            return type;
        }
    }
    
}

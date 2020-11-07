package org.nabuage.blog.persistence;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;


/**
 *
 * @author George
 */
@Entity
@Table(name = "contactmessage")
@NamedQueries({
    @NamedQuery(name = ContactMessageEntity.QUERY_ALL, query = "SELECT cm FROM ContactMessageEntity cm"),
    @NamedQuery(name = ContactMessageEntity.QUERY_BY_ID, query = "SELECT cm FROM ContactMessageEntity cm WHERE cm.id = :id")
})
public class ContactMessageEntity extends AbstractEntity implements Serializable {
    
    public final static String QUERY_ALL = "org.nabuage.blog.persistence.ContactMessageEntity.ALL";
    public final static String QUERY_BY_ID = "org.nabuage.blog.persistence.ContactMessageEntity.BY_ID";
    
    @TableGenerator(name="contactmessageidsequence",table="sequencetable",pkColumnName="sequencename",valueColumnName="sequencecount",pkColumnValue="contactmessageid",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="contactmessageidsequence")
    @Id
    @Basic(optional = false)
    @Column(name = "contactmessageid")
    private Long id;
    
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid Email Address")//if the field contains emailAddress address consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "emailaddress")
    private String emailAddress;
    
    @Size(max = 256)
    @Column(name = "name")
    private String name;
    
    @Size(max = 2147483647)
    @Column(name = "message")
    private String message;
    
    @Basic(optional = false)
    @Column(name = "dateadded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    
    @Basic(optional = false)
    @Size(min = 1, max = 256)
    @Column(name = "contactmessageguid")
    private String guid;

    public ContactMessageEntity() {
    }

    /*public ContactMessageEntity(Long id) {
        this.id = id;
    }

    public ContactMessageEntity(Long id, Date dateAdded, String guid) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.guid = guid;
    }*/

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
        if (!(object instanceof ContactMessageEntity)) {
            return false;
        }
        ContactMessageEntity other = (ContactMessageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.nabuage.blog.persistence.ContactMessage[ id=" + id + " ]";
    }

    
}

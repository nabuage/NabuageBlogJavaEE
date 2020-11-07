package org.nabuage.blog.helper;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author George
 */
public class TomEEContainer {
    private static TomEEContainer CONTAINER;
    private static EJBContainer EJBCONTAINER;
    private static DataSource DATASOURCE;
    private static Properties DATASOURCE_PROPERTIES;
    
    public TomEEContainer() {
        
    }
    
    public static TomEEContainer getInstance() {
        if (CONTAINER == null) {
            CONTAINER = new TomEEContainer();
        }
        
        return CONTAINER;
    }
    
    public EJBContainer getEJBContainer() {
        if (EJBCONTAINER == null) {            
            EJBCONTAINER = EJBContainer.createEJBContainer(getDataSourceProperties());
        }
        
        return EJBCONTAINER;
    }
    
    public Properties getDataSourceProperties() {
        if (DATASOURCE_PROPERTIES == null) {
            DATASOURCE_PROPERTIES = new Properties();
            DATASOURCE_PROPERTIES.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitialContextFactory");
            DATASOURCE_PROPERTIES.put("BlogRestPU", "new://Resource?type=DataSource");
            DATASOURCE_PROPERTIES.put("BlogRestPU.JdbcDriver", "org.postgresql.Driver");
            DATASOURCE_PROPERTIES.put("BlogRestPU.JdbcUrl", "jdbc:postgresql://localhost:5432/blog");
            DATASOURCE_PROPERTIES.put("BlogRestPU.userName", "nabuage");
            DATASOURCE_PROPERTIES.put("BlogRestPU.password", "jonnon");
        }
        
        return DATASOURCE_PROPERTIES;
    }
    
    public Object getEJBContainerContextLookup(String name) throws NamingException {
        return getEJBContainer().getContext().lookup("java:global/WEB-INF/" + name);
    }
    
    public DataSource getDataSource() throws NamingException {
        if (DATASOURCE == null) {
            Context context = new InitialContext(getDataSourceProperties());
            DATASOURCE = (DataSource)context.lookup("java:openejb/Resource/BlogRestPU");
        }
        return DATASOURCE;
    }
    
}
